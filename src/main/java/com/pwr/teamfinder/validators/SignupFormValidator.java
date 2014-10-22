package com.pwr.teamfinder.validators;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;

import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.dto.SignupForm;
import com.pwr.teamfinder.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Optional;

@Component
public class SignupFormValidator extends LocalValidatorFactoryBean {


    private UserRepository userRepository;

    @Resource
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignupForm.class);
    }

    @Override
    public void validate(Object obj, Errors errors, final Object... validationHints) {

        super.validate(obj, errors, validationHints);

        if (!errors.hasErrors()) {
            SignupForm signupForm = (SignupForm) obj;
            Optional<User> existing = userRepository.findByEmail(signupForm.getEmail());
            if (existing.isPresent())
                errors.rejectValue("email", "emailNotUnique");
        }

    }

    @Override
    public ExecutableValidator forExecutables() {
        return null;
    }

    @Override
    public ParameterNameProvider getParameterNameProvider() {
        return null;
    }
}