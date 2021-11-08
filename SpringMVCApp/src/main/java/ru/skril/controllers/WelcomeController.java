package ru.skril.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.skril.repository.UserRepository;

@Controller
public class WelcomeController {

//    @Autowired
//    private UserRepository userRepository;

    @GetMapping()
    public String home() {
        return "home";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/admin")
    public String privateHome() {
        return "private_home";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
