package com.hedgerock.spirng.spring_in_action.taco_app.controllers.security_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.hedgerock.spirng.spring_in_action.taco_app.utl.ControllerUtil.HOME_HTML;
import static com.hedgerock.spirng.spring_in_action.taco_app.utl.ControllerUtil.initPageAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(
            Model model
    ) {
        initPageAttributes(model, "Login", "login");
        return HOME_HTML;
    }

    @PostMapping
    public String initLogin() {

        return "redirect:/";
    }
}
