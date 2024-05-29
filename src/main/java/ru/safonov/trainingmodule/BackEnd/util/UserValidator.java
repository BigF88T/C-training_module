package ru.safonov.trainingmodule.BackEnd.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.safonov.trainingmodule.BackEnd.models.User;
import ru.safonov.trainingmodule.BackEnd.services.UserDetailsService;

import java.util.Date;

@Component
public class UserValidator implements Validator {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserValidator(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        try {
            userDetailsService.loadUserByUsername(user.getUsername());

        } catch (UsernameNotFoundException ignored){
            return; // Пользователь с таким именем не найден
        }

        errors.rejectValue("username", "", "Человек с таким именем пользователя уже существует");

    }
}
