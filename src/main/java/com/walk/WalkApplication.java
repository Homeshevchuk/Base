package com.walk;

import com.walk.Entity.User;
import com.walk.Entity.Walk;
import com.walk.QueryResult.SelectWalkResult;
import org.apache.catalina.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ComponentScan
@SpringBootApplication
@EnableNeo4jRepositories
@EnableScheduling
public class WalkApplication implements CommandLineRunner{
	@Autowired
	UserRepository repository;
	@Autowired
	WalksRepository walksRepository;
	public static void main(String[] args) {
		SpringApplication.run(WalkApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("http://localhost:3000");
			}
		};
	}
	@Override
	public void run(String... args) throws Exception {
		if(!repository.findAll().iterator().hasNext()) {
			createPerson1InDB();
			createPerson2InDB();
		}

		/*Walk walk = repository.findOne(new Long(1),-1).getMyWalks().get(0);
		User user = repository.findOne(new Long(7),-1);
		user.getSubscribed().add(walk);
		repository.save(user,-1);*/


	}

	private void createPerson1InDB() {
		User user = new User();
		user.setName("Bogdan");
		user.setSurname("Shevchuk");
		user.setPassword("123123123");
		user.setEmail("bshevchuk1@gmail.com");
		user.setPhoneNumber("+380986780724");
		user.setRegistered(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		user.setImageUrl("/images/1.png");
		List<Walk> walkList = new ArrayList<>();
		Walk walk = new Walk();
		walk.setCompleted(false);
		walk.setMaxUsers(3);
		walk.setStartDate(Date.from(LocalDateTime.now().plusMinutes(300).atZone(ZoneId.systemDefault()).toInstant()));
		walk.addPoint(50.450428,30.523590,"Start1");
		walk.addPoint(50.451328,30.524390,"beetween1");
		walk.addPoint(50.453328,30.525190,"beetween2");
		walk.addPoint(50.455328,30.527190,"end1");
		walk.setTitle("I want to visit kreschatyk");
		walkList.add(walk);

		/*Walk walk1 = new Walk();

		walk1.setCompleted(false);
		walk1.setMaxUsers(4);
		walk1.addPoint(50.446055,30.513365,"start2");
		walk1.addPoint(50.447055,30.515365,"beetween2");
		walk1.addPoint(50.449255,30.518365,"end2");
		walk1.setTitle("I want to visit Mother Motherland");
		walkList.add(walk1);
*/

		user.setMyWalks(walkList);
		repository.save(user,-1);

	}
	private void createPerson2InDB() {
		User user = new User();
		user.setName("Nikita");
		user.setSurname("Strigunkov");
		user.setEmail("strignik@gmail.com");
		user.setPassword("123123123");
		user.setPhoneNumber("+380986780724");
		user.setRegistered(new Date());
		user.setImageUrl("/images/2.png");
		List<Walk> walkList = new ArrayList<>();
		Walk walk = new Walk();
		walk.setCompleted(false);
		walk.setMaxUsers(1);
		walk.setStartDate(Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault()).toInstant()));
		walk.addPoint(50.450837,30.510254,"asd");
		walk.addPoint(50.453937,30.513554,"ads");
		walk.addPoint(50.456937,30.516554,"asdasd");
		walk.setTitle("Want walk a mile");

		walkList.add(walk);
		user.setMyWalks(walkList);
		repository.save(user,-1);
	}
}
