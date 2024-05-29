package ru.safonov.trainingmodule.BackEnd.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.safonov.trainingmodule.BackEnd.services.UserDetailsService;

@Controller
public class AdminController {
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userDetailsService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) int id,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userDetailsService.deleteUser(id);
        }
        return "redirect:/admin";
    }

}
