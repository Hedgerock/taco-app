package com.hedgerock.spirng.spring_in_action.view_taco_app.data.rest_repositories;


import com.hedgerock.spirng.spring_in_action.view_taco_app.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoCrudRepository extends CrudRepository<Taco, Long> {
}
