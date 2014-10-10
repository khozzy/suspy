package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private Users repository;

    public User save(final User user) {
        return repository.save(user);
    }

    public void someMethod() {
        System.out.println("Calling some method from service");
    }

}
