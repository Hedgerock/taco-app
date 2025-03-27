package com.hedgerock.spirng.spring_in_action.view_taco_app.controllers;


import com.hedgerock.spirng.spring_in_action.view_taco_app.data.ingredients_repository.IngredientsRepository;
import com.hedgerock.spirng.spring_in_action.view_taco_app.model.Ingredients;
import com.hedgerock.spirng.spring_in_action.view_taco_app.model.Ingredients.Type;
import com.hedgerock.spirng.spring_in_action.view_taco_app.model.Taco;
import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;
import com.hedgerock.spirng.spring_in_action.view_taco_app.model.security.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

import static com.hedgerock.spirng.spring_in_action.view_taco_app.util.ControllerUtil.HOME_HTML;
import static com.hedgerock.spirng.spring_in_action.view_taco_app.util.ControllerUtil.initPageAttributes;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientsRepository ingredientsRepository;

    public DesignTacoController(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(
            Model model
    ) {
        Iterable<Ingredients>ingredients = this.ingredientsRepository.findAll();

        Type[] types = Type.values();

        for (Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

    }

    @ModelAttribute
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute
    public TacoOrder tacoOrder(
            @AuthenticationPrincipal User user
    ) {
        TacoOrder tacoOrder = new TacoOrder();
        tacoOrder.insertAuthorizedUserDetails(user);

        return tacoOrder;
    }

    @GetMapping
    public String showDesignForm(
            Model model
    ) {
        initPageAttributes(model, "Taco design page", "design");
        return HOME_HTML;
    }

    @PostMapping
    public String processTaco(
            @Valid Taco taco,
            BindingResult bindingResult,
            @ModelAttribute TacoOrder tacoOrder,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return showDesignForm(model);
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco:{}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredients>filterByType(Iterable<Ingredients> ingredients, Type type) {

        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(ingredient -> ingredient.getType().equals(type))
                .toList();
    }

}
