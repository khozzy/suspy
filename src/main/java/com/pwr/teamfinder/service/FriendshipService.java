package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

@Service
@Transactional
public class FriendshipService {

    public void addFriend(User user, User newFriend) {
        System.out.println("Calling some method from service FriendshipService");
    }

    public void rejectFriend(User user, User rejectedFriend) {
        System.out.println("rejecting friend");
    }

    public Set<User> showFriends(User user) {
        System.out.println("show all friends of the user");
        return Collections.emptySet();
    }

}
