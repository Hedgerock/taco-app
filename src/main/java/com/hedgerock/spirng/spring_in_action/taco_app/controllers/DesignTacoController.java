package com.hedgerock.spirng.spring_in_action.taco_app.controllers;

import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients;
import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients.Type;
import com.hedgerock.spirng.spring_in_action.taco_app.model.Taco;
import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients.Type.*;
import static com.hedgerock.spirng.spring_in_action.taco_app.utl.ControllerUtil.HOME_HTML;
import static com.hedgerock.spirng.spring_in_action.taco_app.utl.ControllerUtil.initPageAttributes;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel(
            Model model
    ) {
        List<Ingredients>ingredients = Arrays.asList(
                new Ingredients("FLTO", "Flour Tortilla", WRAP),
                new Ingredients("COTO", "Corn Tortilla", WRAP),
                new Ingredients("GRBF", "Ground Beef", PROTEIN),
                new Ingredients("CARN", "Carnitas", PROTEIN),
                new Ingredients("TMTO", "Diced Tomatoes", VEGETABLES),
                new Ingredients("LETC", "Lettuce", VEGETABLES),
                new Ingredients("CHED", "Cheddar", CHEESE),
                new Ingredients("JACK", "Monterrey Jack", CHEESE),
                new Ingredients("SLSA", "Salsa", SAUCE),
                new Ingredients("SRCR", "Sour Cream", SAUCE)
        );

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
    public TacoOrder tacoOrder() {
        return new TacoOrder();
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

    private Iterable<Ingredients>filterByType(List<Ingredients> ingredients, Type type) {

        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .toList();
    }

}
