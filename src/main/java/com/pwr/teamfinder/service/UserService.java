package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.generic.service.GenericServiceImpl;
import com.pwr.teamfinder.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericServiceImpl<User, Long, Users> {

    @Autowired
    private Users repository;

    @Override
    public Users getRepository() {
        return repository;
    }

    public void someMethod() {
        System.out.println("Calling some method from service User");
    }

}
