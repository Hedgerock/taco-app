package com.hedgerock.spirng.spring_in_action.view_taco_app.rest_client;

import com.hedgerock.spirng.spring_in_action.view_taco_app.model.Ingredients;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TacoCloudRestClient {

    protected final RestTemplate restTemplate;

    public TacoCloudRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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

}
