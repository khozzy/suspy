package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Address;
import com.pwr.teamfinder.domain.Role;
import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.dto.SignupForm;
import com.pwr.teamfinder.exception.UserAlreadyExistsException;
import com.pwr.teamfinder.repository.UserRepository;
import com.pwr.teamfinder.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService extends GenericServiceImpl<User, Long, UserRepository> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User signup(final SignupForm signupForm) throws UserAlreadyExistsException {

        final String name = signupForm.getName();
        final String surname = signupForm.getSurname();
        final String email = signupForm.getEmail();
        final String password = signupForm.getPassword();
        final Role role = Role.valueOf(signupForm.getRole());
        final String city = signupForm.getCity();
        final String houseNo = signupForm.getHouseNumber();
        final String street = signupForm.getStreet();
        final String about = signupForm.getAbout();

        Optional<User> existing = userRepository.findByEmail(email);

        if (existing.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Address address = new Address(street, houseNo, city);

        User newUser = new User();

        newUser.setCreatedDate(new Date());
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setEmail(email);
        newUser.setPassword(hashPassword(password));
        newUser.setRole(role);
        newUser.setAddress(address);
        newUser.setAbout(about);

        return userRepository.save(newUser);
    }

    private String hashPassword(final String password) {
        // metoda hashujaca haslo dla uzytkownika
        return password;
    }
}
