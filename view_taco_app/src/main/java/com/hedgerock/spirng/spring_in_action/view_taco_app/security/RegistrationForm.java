package com.hedgerock.spirng.spring_in_action.view_taco_app.security;

import com.hedgerock.spirng.spring_in_action.view_taco_app.model.security.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                this.username,
                passwordEncoder.encode(this.password),
                this.fullName,
                this.street,
                this.city,
                this.state,
                this.zip,
                this.phoneNumber
        );
    }
}
