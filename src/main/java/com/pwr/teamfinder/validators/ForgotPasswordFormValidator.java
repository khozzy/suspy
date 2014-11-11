package com.pwr.teamfinder.validators;

import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.dto.ForgotPasswordForm;
import com.pwr.teamfinder.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;
import java.util.Optional;

@Component
public class ForgotPasswordFormValidator extends LocalValidatorFactoryBean {


    private Users userRepository;

    @Autowired
    public ForgotPasswordFormValidator(Users userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ForgotPasswordForm.class);
    }

    @Override
    public void validate(Object obj, Errors errors, final Object... validationHints) {

        super.validate(obj, errors, validationHints);

        if (!errors.hasErrors()) {
            ForgotPasswordForm forgotPasswordForm = (ForgotPasswordForm) obj;
            Optional<User> existing = userRepository.findByEmail(forgotPasswordForm.getEmail());

            if (!existing.isPresent()) {
                errors.rejectValue("email", "email.error.notFound");
            }
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