package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Address;
import com.pwr.teamfinder.domain.Role;
import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.dto.SignupForm;
import com.pwr.teamfinder.exception.UserAlreadyExistsException;
import com.pwr.teamfinder.repository.Users;
import com.pwr.teamfinder.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService extends GenericServiceImpl<User, Long, Users> {

    @Autowired
    private Users repository;

    @Override
    public Users getRepository() {
        return repository;
    }

    public User createNewUser(final SignupForm signupForm) throws UserAlreadyExistsException {

        final String name = signupForm.getName();
        final String surname = signupForm.getSurname();
        final String email = signupForm.getEmail();
        final String password = signupForm.getPassword();
        final Role role = Role.valueOf(signupForm.getRole());
        final String city = signupForm.getCity();
        final String houseNo = signupForm.getHouseNumber();
        final String street = signupForm.getStreet();
        final String about = signupForm.getAbout();

        Optional<User> existing = repository.findByEmail(email);

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

        return repository.save(newUser);
    }

    private String hashPassword(final String password) {
        // metoda hashujaca haslo dla uzytkownika
        return password;
    }
}
