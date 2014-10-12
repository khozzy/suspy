package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.Friend;
import com.pwr.teamfinder.repository.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {

    @Autowired
    private Friends repository;

    public Friend save(final Friend friend) {
        return repository.save(friend);
    }

    public void someMethod() {
        System.out.println("Calling some method from service FriendService");
    }

}
