package com.hedgerock.spirng.spring_in_action.view_taco_app.config;

import com.hedgerock.spirng.spring_in_action.view_taco_app.model.security.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object userDetails = authentication.getPrincipal();

            if(userDetails instanceof User) {
                request.setAttribute("authorizedUser", userDetails);
            }
        }

        return true;
    }
}
