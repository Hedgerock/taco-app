package com.hedgerock.spirng.spring_in_action.view_taco_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.hedgerock.spirng.spring_in_action.view_taco_app.util.ControllerUtil.HOME_HTML;
import static com.hedgerock.spirng.spring_in_action.view_taco_app.util.ControllerUtil.initPageAttributes;


@Controller
public class HomeController {

    @GetMapping("/")
    public String homepage(
            Model model
    ) {
        initPageAttributes(model, "Taco Cloud", "main");
        return HOME_HTML;
    }

}
