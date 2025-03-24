package com.hedgerock.spirng.spring_in_action.taco_app.data.security_repository;

import com.hedgerock.spirng.spring_in_action.taco_app.model.security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
