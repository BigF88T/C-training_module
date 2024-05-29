package ru.safonov.trainingmodule.BackEnd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import ru.safonov.trainingmodule.BackEnd.security.UsersDetails;
import ru.safonov.trainingmodule.BackEnd.services.AdminService;
import ru.safonov.trainingmodule.BackEnd.services.UserDetailsService;

@Controller
public class MainController {
    private final AdminService adminService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public MainController(AdminService adminService, UserDetailsService userDetailsService) {
        this.adminService = adminService;
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/")
    @Transactional
    public String sayHello(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails usersDetails = (UsersDetails)authentication.getPrincipal();
        usersDetails.setLastVisit();
        return "index";
    }

    @GetMapping("/showUserInfo")
    public String showInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails usersDetails = (UsersDetails)authentication.getPrincipal();
        System.out.println(usersDetails.getUser());

        return "articles/article_index";
    }

    @GetMapping("/training_module")
    public String trainingModule(){
        return "training_module";
    }

    @GetMapping("/about")
    public String aboutUs(){
        return "about";
    }
}
