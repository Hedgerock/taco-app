package com.hedgerock.spirng.spring_in_action.taco_app.rest_client;

import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients;
import com.hedgerock.spirng.spring_in_action.taco_app.model.Taco;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TacoCloudRestClient {

    private final RestTemplate restTemplate;
    private final Traverson traverson;

    public TacoCloudRestClient(RestTemplate restTemplate, Traverson traverson) {
        this.restTemplate = restTemplate;
        this.traverson = traverson;
    }

    public Ingredients getIngredientsById(String ingredientId) {


        return this.restTemplate.getForObject(
                "http://localhost:8080/ingredients/{id}",
                Ingredients.class,
                ingredientId
        );
    }

    public List<Ingredients>getAllIngredients() {

        return this.restTemplate.exchange(
                "http://localhost:8080/ingredients",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ingredients>>() {}
        ).getBody();
    }

    public void updateIngredients(Ingredients ingredients) {
        this.restTemplate.put(
                "http://localhost:8080/ingredients/{id}",
                ingredients,
                ingredients.getId()
        );
    }

    public Ingredients createIngredient(Ingredients ingredients) {

        return this.restTemplate.postForObject(
                "http://localhost:8080/ingredients",
                ingredients,
                Ingredients.class
        );

    }

    public void deleteIngredient(Ingredients ingredients) {

        this.restTemplate.delete(
                "http://localhost:8080/ingredients{id}",
                ingredients.getId()
        );
    }

    //Traverson option

    public Iterable<Ingredients> getAllIngredientsWithTraversion() {

        ParameterizedTypeReference<CollectionModel<Ingredients>> ingredientType =
                new ParameterizedTypeReference<CollectionModel<Ingredients>>() {};

        CollectionModel<Ingredients> ingredientsRes =
                this.traverson
                        .follow("ingredients")
                        .toObject(ingredientType);

        return ingredientsRes != null ? ingredientsRes.getContent() : null;
    }

    public Ingredients addIngredientWithTraversion(Ingredients ingredients) {

        String ingredientsUrl = this.traverson
                .follow("ingredients")
                .asLink()
                .getHref();

        return this.restTemplate.postForObject(ingredientsUrl, ingredients, Ingredients.class);
    }


    public Iterable<Taco>getRecentTacosWithTraversion() {
        ParameterizedTypeReference<CollectionModel<Taco>> tacoType =
                new ParameterizedTypeReference<CollectionModel<Taco>>() {};

        CollectionModel<Taco> tacoRes =
                this.traverson
                        .follow("/tacos")
                        .follow("recents")
                        .toObject(tacoType);

        return tacoRes != null ? tacoRes.getContent() : null;
    }



}
