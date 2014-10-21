package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Address;
import com.pwr.teamfinder.domain.Role;
import com.pwr.teamfinder.domain.User;
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

    public User createNewUser(
            final String name,
            final String surname,
            final String email,
            final String password,
            final Role role,
            final String city,
            final String houseNo,
            final String street,
            final String about) throws UserAlreadyExistsException {

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
