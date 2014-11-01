package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Address;
import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.domain.Role;
import com.pwr.teamfinder.dto.ForgotPasswordForm;
import com.pwr.teamfinder.dto.ResetPasswordForm;
import com.pwr.teamfinder.dto.SignupForm;
import com.pwr.teamfinder.dto.UserDetailsImpl;
import com.pwr.teamfinder.exception.UserAlreadyExistsException;
import com.pwr.teamfinder.mail.MailSender;
import com.pwr.teamfinder.repository.UserRepository;
import com.pwr.teamfinder.service.generic.GenericServiceImpl;

import com.pwr.teamfinder.util.MyUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.validation.BindingResult;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService extends GenericServiceImpl<User, Long, UserRepository> implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private MailSender mailSender;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, MailSender mailSender){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User signup(final SignupForm signupForm) throws UserAlreadyExistsException {

        final String name = signupForm.getName();
        final String surname = signupForm.getSurname();
        final String email = signupForm.getEmail();
        final String password = signupForm.getPassword().trim();
        final Role role = Role.valueOf(signupForm.getRole());
        final String city = signupForm.getCity();
        final String houseNo = signupForm.getHouseNumber();
        final String street = signupForm.getStreet();
        final String about = signupForm.getAbout();

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        Address address = new Address(street, houseNo, city);

        final User newUser = new User();

        newUser.setCreatedDate(new Date());
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.getRoles().add(Role.UNVERIFIED);
        newUser.getRoles().add(role);
        newUser.setAddress(address);
        newUser.setAbout(about);
        newUser.setVerificationCode(RandomStringUtils.randomAlphanumeric(User.RANDOM_CODE_LENGTH));

        userRepository.save(newUser);

        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCommit() {
                        try {
                            String verifyLink = MyUtil.hostUrl() + "/users/" + newUser.getVerificationCode() + "/verify";
                            mailSender.send(newUser.getEmail(), MyUtil.getMessage("verifySubject"), MyUtil.getMessage("verifyEmail", verifyLink));
                            logger.info("Verification mail to " + newUser.getEmail() + " queued.");
                        } catch (MessagingException e) {
                            logger.error(ExceptionUtils.getStackTrace(e));
                        }
                    }
                });

        return newUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        //username==email address
        Optional<User> user = userRepository.findByEmail(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(user.get());
    }

    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void verify(String verificationCode) {

        long loggedInUserId = MyUtil.getSessionUser().getId();
        User user = userRepository.findOne(loggedInUserId);

        MyUtil.validate(user.getRoles().contains(Role.UNVERIFIED), "alreadyVerified");
        MyUtil.validate(user.getVerificationCode().equals(verificationCode),
                "incorrect", MyUtil.getMessage("verificationCode"));

        user.getRoles().remove(Role.UNVERIFIED);
        user.setVerificationCode(null);
        userRepository.save(user);

    }

    public void resendVerificationEmail() {

        long loggedInUserId = MyUtil.getSessionUser().getId();
        User user = userRepository.findOne(loggedInUserId);

        try {
            String verifyLink = MyUtil.hostUrl() + "/users/" + user.getVerificationCode() + "/verify";
            mailSender.send(user.getEmail(), MyUtil.getMessage("verifySubject"), MyUtil.getMessage("verifyEmail", verifyLink));
            logger.info("Verification mail to " + user.getEmail() + " queued.");
        } catch (MessagingException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void forgotPassword(ForgotPasswordForm form){

        final Optional<User> existing = userRepository.findByEmail(form.getEmail());
        final User user = existing.get();
        final String resetPasswordCode = RandomStringUtils.randomAlphanumeric(User.RANDOM_CODE_LENGTH);

        user.setResetPasswordCode(resetPasswordCode);
        final User savedUser = userRepository.save(user);

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

    private void mailResetPasswordLink(User user) throws MessagingException {

        String resetPasswordLink =
                MyUtil.hostUrl() + "/reset-password/" +
                        user.getId()+"/"+
                        user.getResetPasswordCode();
        mailSender.send(user.getEmail(),
                MyUtil.getMessage("resetPasswordSubject"),
                MyUtil.getMessage("resetPasswordEmail", resetPasswordLink));

    }

    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void resetPassword(Long userID,
                              ResetPasswordForm resetPasswordForm) {

        User user = userRepository.findOne(userID);

        user.setResetPasswordCode(null);
        user.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword().trim()));
        userRepository.save(user);
    }

}
