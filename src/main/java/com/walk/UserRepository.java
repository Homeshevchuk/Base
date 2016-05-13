package com.walk;

import com.walk.Entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bogdan on 5/13/2016.
 */
@Repository
public interface UserRepository extends GraphRepository<User> {
}
