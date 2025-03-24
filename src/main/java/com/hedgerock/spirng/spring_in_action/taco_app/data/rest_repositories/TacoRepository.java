package com.hedgerock.spirng.spring_in_action.taco_app.data.rest_repositories;

import com.hedgerock.spirng.spring_in_action.taco_app.model.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
