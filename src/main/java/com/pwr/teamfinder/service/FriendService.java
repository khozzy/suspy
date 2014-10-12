package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Friend;
import com.pwr.teamfinder.generic.service.GenericServiceImpl;
import com.pwr.teamfinder.repository.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService extends GenericServiceImpl<Friend, Long, Friends> {

    @Autowired
    private Friends repository;

    @Override
    public Friends getRepository() {
        return repository;
    }

    public void someMethod() {
        System.out.println("Calling some method from service FriendService");
    }

}
