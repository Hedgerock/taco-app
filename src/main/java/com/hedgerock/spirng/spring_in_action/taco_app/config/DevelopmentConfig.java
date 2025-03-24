package com.hedgerock.spirng.spring_in_action.taco_app.config;

import com.hedgerock.spirng.spring_in_action.taco_app.data.ingredients_repository.IngredientsRepository;
import com.hedgerock.spirng.spring_in_action.taco_app.data.security_repository.UserRepository;
import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients;
import com.hedgerock.spirng.spring_in_action.taco_app.model.security.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients.Type.*;

@Configuration
public class DevelopmentConfig {


    @Bean
    CommandLineRunner dataLoader(
            IngredientsRepository ingredientsRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {


        return args -> {
            ingredientsRepository.deleteAll();
            userRepository.deleteAll();

            ingredientsRepository.save(new Ingredients("FLTO", "Flour Tortilla", WRAP));
            ingredientsRepository.save(new Ingredients("COTO", "Corn Tortilla", WRAP));
            ingredientsRepository.save(new Ingredients("GRBF", "Ground Beef", PROTEIN));
            ingredientsRepository.save(new Ingredients("CARN", "Carnitas", PROTEIN));
            ingredientsRepository.save(new Ingredients("TMTO", "Diced Tomatoes", VEGETABLES));
            ingredientsRepository.save(new Ingredients("LETC", "Lettuce", VEGETABLES));
            ingredientsRepository.save(new Ingredients("CHED", "Cheddar", CHEESE));
            ingredientsRepository.save(new Ingredients("JACK", "Monterrey Jack", CHEESE));
            ingredientsRepository.save(new Ingredients("SLSA", "Salsa", SAUCE));
            ingredientsRepository.save(new Ingredients("SRCR", "Sour Cream", SAUCE));

            userRepository.save(new User("hedgerock", passwordEncoder.encode("1234"),
                    "Craig Walls", "123 North Street", "Cross Roads", "TX",
                    "76227", "123-123-1234"));
        };
    }

}

