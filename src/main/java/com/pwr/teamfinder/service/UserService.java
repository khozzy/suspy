package com.pwr.teamfinder.service;

import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.dto.SignupForm;
import com.pwr.teamfinder.exception.UserAlreadyExistsException;

public interface UserService {

    public abstract User signup(SignupForm signupForm) throws UserAlreadyExistsException;

}
