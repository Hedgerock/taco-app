package com.hedgerock.spirng.spring_in_action.taco_app.controllers;

import com.hedgerock.spirng.spring_in_action.taco_app.data.order_repository.OrderRepository;
import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients;
import com.hedgerock.spirng.spring_in_action.taco_app.model.Taco;
import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import static com.hedgerock.spirng.spring_in_action.taco_app.utl.ControllerUtil.HOME_HTML;
import static com.hedgerock.spirng.spring_in_action.taco_app.utl.ControllerUtil.initPageAttributes;

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

        initPageAttributes(model, "Order page", "order");
        return HOME_HTML;
    }

    @PostMapping
    public String initDelivery(
            @Valid TacoOrder tacoOrder,
            BindingResult bindingResult,
            SessionStatus sessionStatus,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return orderForm(model);
        }

        this.orderRepository.saveTacoOrder(tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
