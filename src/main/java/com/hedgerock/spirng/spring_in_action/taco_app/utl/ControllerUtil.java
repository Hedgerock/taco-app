package com.hedgerock.spirng.spring_in_action.taco_app.utl;

import org.springframework.ui.Model;

public class ControllerUtil {
    public static final String HOME_HTML = "home.html";

    public static void initPageAttributes(Model model, String pageTitle, String pageName) {
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("pageName", pageName);
    }

    private ControllerUtil() {}

}
