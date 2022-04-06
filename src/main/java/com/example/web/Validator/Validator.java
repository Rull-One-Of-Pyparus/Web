package com.example.web.Validator;

import com.example.web.Config.Entity.User;
import com.example.web.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;


@RequiredArgsConstructor
public class Validator implements org.springframework.validation.Validator {

    private final UserService userService;
    private static final String LOGIN_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]+$";

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(userService.userByName(user.getUsername())!=null){
            errors.rejectValue("username","validation","username is existed");
        }

        if(!user.getLogin().matches(LOGIN_PATTERN)) {
            errors.rejectValue("login","validation","login invalid");
        }

        if(!user.getLogin().matches(PASSWORD_PATTERN)) {
            errors.rejectValue("password","validation","password invalid");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("password","validation","password not existed");
        }
    }
}
