package com.example.web.Controllers;

import com.example.web.Config.Entity.User;
import com.example.web.Validator.Validator;
import com.example.web.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final Validator validator;

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "home";
    }

    @PostMapping("/registration")
    public String reg(@ModelAttribute("newUser") User user, HttpServletRequest request, BindingResult bindingResult) throws ServletException {

        validator.validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "registration";
        } else {
            String password = user.getPassword();
            userService.save(user);
            request.login(user.getUsername(), password);
            return "redirect:/home";
        }
    }

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @RequestMapping(value = {"/login", "/"})
    public String login() {
        return "login";
    }

}
