package com.hedgerock.spirng.spring_in_action.taco_app.rest_client;

import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients;
import com.hedgerock.spirng.spring_in_action.taco_app.model.Taco;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TacoCloudTraversonClient extends TacoCloudRestClient {

    private final Traverson traverson;

    public TacoCloudTraversonClient(RestTemplate restTemplate, Traverson traverson) {
        super(restTemplate);
        this.traverson = traverson;
    }

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
