package com.hedgerock.spirng.spring_in_action.view_taco_app.controllers.security_controllers;

import com.hedgerock.spirng.spring_in_action.view_taco_app.security.LoginForm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.hedgerock.spirng.spring_in_action.view_taco_app.util.ControllerUtil.HOME_HTML;
import static com.hedgerock.spirng.spring_in_action.view_taco_app.util.ControllerUtil.initPageAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String login(
            Model model
    ) {
        model.addAttribute("loginForm", new LoginForm());
        initPageAttributes(model, "Login", "login");
        return HOME_HTML;
    }

    @PostMapping
    public String initLogin(@ModelAttribute LoginForm loginForm) {
        UserDetails user = this.userDetailsService.loadUserByUsername(loginForm.getUsername());

        if (!user.getUsername().equals(loginForm.getUsername()) ||
                !this.passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
            return "redirect:/login";
        }

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/";
    }
}
