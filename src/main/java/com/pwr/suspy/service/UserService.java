package com.pwr.suspy.service;

import com.pwr.suspy.domain.Address;
import com.pwr.suspy.domain.Role;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.dto.SignupForm;
import com.pwr.suspy.dto.UserDetailsImpl;
import com.pwr.suspy.dto.UserEditForm;
import com.pwr.suspy.exception.UserAlreadyExistsException;
import com.pwr.suspy.exception.UserAlreadyObservedException;
import com.pwr.suspy.mail.MailSender;
import com.pwr.suspy.repository.Users;
import com.pwr.suspy.service.generic.GenericServiceImpl;
import com.pwr.suspy.util.MyUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService extends GenericServiceImpl<User, Long, Users> implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private Users users;
    private PasswordEncoder passwordEncoder;
    private MailSender mailSender;

    @Autowired
    public UserService(Users userRepository, PasswordEncoder passwordEncoder, MailSender mailSender) {

        this.users = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Override
    public Users getRepository() {
        return users;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User createNewUser(final User user) throws UserAlreadyExistsException {

        if(emailExist(user.getEmail())) throw new UserAlreadyExistsException("User already exists");

        user.setCreatedDate(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.UNVERIFIED);
        user.setVerificationCode(RandomStringUtils.randomAlphanumeric(User.RANDOM_CODE_LENGTH));

        users.save(user);

        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCommit() {
                        try {
                            String verifyLink = MyUtil.hostUrl() + "/users/" + user.getVerificationCode() + "/verify";
                            mailSender.send(user.getEmail(), MyUtil.getMessage("verifySubject"), MyUtil.getMessage("verifyEmail", verifyLink));
                            logger.info("Verification mail to " + user.getEmail() + " queued.");
                        } catch (MessagingException e) {
                            logger.error(ExceptionUtils.getStackTrace(e));
                        }
                    }
                });

        return user;
    }

    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void updateUser(long userId, UserEditForm userEditForm) {

        User loggedIn = MyUtil.getSessionUser();
        MyUtil.validate(loggedIn.isAdmin() || loggedIn.getId() == userId, "noPermissions");
        User user = users.findOne(userId);
        user.setName(userEditForm.getName());
        if (loggedIn.isAdmin())
            user.setRoles(userEditForm.getRoles());
        users.save(user);

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void verifyUser(final String verificationCode) {

        Long loggedInUserId = MyUtil.getSessionUser().getId();
        User user = users.findOne(loggedInUserId);

        MyUtil.validate(user.getRoles().contains(Role.UNVERIFIED), "alreadyVerified");
        MyUtil.validate(user.getVerificationCode().equals(verificationCode), "incorrect", MyUtil.getMessage("verificationCode"));

        user.getRoles().remove(Role.UNVERIFIED);
        user.setVerificationCode(null);
        users.save(user);

        //to check if password change is doing well when it is used
        UserDetails userDetails = new UserDetailsImpl(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void forgotPassword(final String email) {
        final User user = users.findByEmail(email).get();
        final String resetPasswordCode = RandomStringUtils.randomAlphanumeric(User.RANDOM_CODE_LENGTH);
        user.setResetPasswordCode(resetPasswordCode);
        final User savedUser = users.save(user);

        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCommit() {
                        try {
                            mailResetPasswordLink(savedUser);
                        } catch (MessagingException e) {
                            logger.error(ExceptionUtils.getStackTrace(e));
                        }
                    }

                });

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void resetPassword(String resetPasswordCode, String newPassword) {
        User user = findByResetPasswordCode(resetPasswordCode).get();
        user.setResetPasswordCode(null);
        user.setPassword(passwordEncoder.encode(newPassword));
        users.save(user);
    }

    @Transactional
    public void observe(final User userA, final User userB) throws UserAlreadyObservedException {
        userA.startObserving(userB);
        save(userA);
    }

    @Transactional
    public void stopObserving(final User userA, final User userB) {
        userA.stopObserving(userB);
        save(userA);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (!emailExist(username)) {
            throw new UsernameNotFoundException(username);
        }
        User user = users.findByEmail(username).get();
        return new UserDetailsImpl(user);
    }

    public Optional<User> findByResetPasswordCode(String resetPasswordCode) {
        return users.findByResetPasswordCode(resetPasswordCode);
    }

    private void mailResetPasswordLink(User user) throws MessagingException {
        String resetPasswordLink =
                MyUtil.hostUrl() + "/reset-password/" +
                        user.getResetPasswordCode();
        mailSender.send(user.getEmail(),
                MyUtil.getMessage("resetPasswordSubject"),
                MyUtil.getMessage("resetPasswordEmail", resetPasswordLink));
    }

    public void resendVerificationEmail() {

        long loggedInUserId = MyUtil.getSessionUser().getId();
        User user = users.findOne(loggedInUserId);

        try {
            String verifyLink = MyUtil.hostUrl() + "/users/" + user.getVerificationCode() + "/verify";
            mailSender.send(user.getEmail(), MyUtil.getMessage("verifySubject"), MyUtil.getMessage("verifyEmail", verifyLink));
            logger.info("Verification mail to " + user.getEmail() + " queued.");
        } catch (MessagingException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public boolean emailExist(String email){
        Optional<User> existing = users.findByEmail(email);
        return existing.isPresent();
    }

    public User convertSignUpFormToUser(final SignupForm signupForm){

        final String street = signupForm.getStreet();
        final String houseNo = signupForm.getHouseNumber();
        final String city = signupForm.getCity();

        final Address address = new Address(street, houseNo, city);

        final User user = new User();

        user.setName(signupForm.getName());
        user.setSurname(signupForm.getSurname());
        user.setEmail(signupForm.getEmail());
        user.setPassword(signupForm.getPassword());
        user.getRoles().add(Role.valueOf(signupForm.getRole()));
        user.setAddress(address);
        user.setAbout(signupForm.getAbout());

        return user;
    }

    public User checkPermissions(User user) {

        User loggedIn = MyUtil.getSessionUser();

        if (loggedIn == null || loggedIn.getId() != user.getId() && !loggedIn.isAdmin()){
            user.setPassword(null);
            user.setVerificationCode(null);
            user.setResetPasswordCode(null);
            user.setCreatedDate(null);
        }

        return user;
    }
}
