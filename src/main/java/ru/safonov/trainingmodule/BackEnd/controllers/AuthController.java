package ru.safonov.trainingmodule.BackEnd.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.safonov.trainingmodule.BackEnd.models.User;
import ru.safonov.trainingmodule.BackEnd.services.RegistrationService;
import ru.safonov.trainingmodule.BackEnd.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserValidator userValidator;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(UserValidator userValidator, RegistrationService registrationService) {
        this.userValidator = userValidator;
        this.registrationService = registrationService;

    }


    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user")User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult, Model model){
        userValidator.validate(user, bindingResult);// Валидация данных, введенных в форму

        if (bindingResult.hasErrors()) //Обработка выброса исключений
            return "/auth/registration";

        registrationService.register(user); // Регистрация нового пользователя через RegistrationService

        return "redirect:/auth/login"; // Перенаправление на форму с login`ом

    }
}
