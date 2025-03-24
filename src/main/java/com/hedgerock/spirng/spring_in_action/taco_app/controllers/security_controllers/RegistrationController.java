package com.hedgerock.spirng.spring_in_action.taco_app.controllers.security_controllers;

import com.hedgerock.spirng.spring_in_action.taco_app.data.security_repository.UserRepository;
import com.hedgerock.spirng.spring_in_action.taco_app.security.RegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.hedgerock.spirng.spring_in_action.taco_app.utl.ControllerUtil.HOME_HTML;
import static com.hedgerock.spirng.spring_in_action.taco_app.utl.ControllerUtil.initPageAttributes;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(
            Model model
    ) {
        model.addAttribute("registrationForm", new RegistrationForm());
        initPageAttributes(model, "Registration", "registration");
        return HOME_HTML;
    }

    @PostMapping
    public String processRegistration(@ModelAttribute RegistrationForm registrationForm) {
        this.userRepository.save(registrationForm.toUser(this.passwordEncoder));

        return "redirect:/login";
    }
}
