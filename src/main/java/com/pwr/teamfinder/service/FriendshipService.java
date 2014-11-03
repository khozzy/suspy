package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class FriendshipService {

    public void sendFriendRequest(final User user, final User newFriend) {
        // Dodanie znajomego
    }

    public void cancelFriendRequest(final User user, final User newFriend) {
        // Anuluje wyslane zaproszenie do grona znajomych
    }

    public void rejectFriendRequest(final User user, final User rejectedFriend) {
        // odrzucenie zaproszenia do grona znajomych
    }

    public void removeFriend(final User user, final User friendToBeRemoved) {
        // Usuwanie usera z grona znajomych
    }

    public Set<User> showFriends(final User user) {
        // wyswietleni listy wszystkich znajomych
        return null;
    }

}
