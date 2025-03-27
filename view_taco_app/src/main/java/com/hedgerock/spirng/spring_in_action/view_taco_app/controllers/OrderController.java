package com.hedgerock.spirng.spring_in_action.view_taco_app.controllers;


import com.hedgerock.spirng.spring_in_action.view_taco_app.data.order_repository.OrderRepository;
import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;
import com.hedgerock.spirng.spring_in_action.view_taco_app.model.security.User;
import com.hedgerock.spirng.spring_in_action.view_taco_app.util.ControllerUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import static com.hedgerock.spirng.spring_in_action.view_taco_app.util.ControllerUtil.HOME_HTML;

@Controller
@RequestMapping("/orders")
@Slf4j
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @GetMapping("/current")
    public String orderForm(
            Model model
    ) {
        if (model.getAttribute("tacoOrder") == null) {
            return "redirect:/design";
        }

        ControllerUtil.initPageAttributes(model, "Order page", "order");
        return HOME_HTML;
    }

    @PostMapping
    public String initDelivery(
            @Valid TacoOrder tacoOrder,
            BindingResult bindingResult,
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return orderForm(model);
        }

        tacoOrder.setUser(user);

        this.orderRepository.save(tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
