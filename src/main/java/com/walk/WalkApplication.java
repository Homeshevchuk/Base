package com.walk;

import com.walk.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@ComponentScan
@SpringBootApplication
@EnableNeo4jRepositories
public class WalkApplication implements CommandLineRunner{
	@Autowired
	UserRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(WalkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
