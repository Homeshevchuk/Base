package com.walk;

import com.walk.Entity.User;
import com.walk.Entity.Walk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan on 5/13/2016.
 */
@RestController
@ComponentScan
public class Rest {
    @Autowired
    UserRepository repository;
    @RequestMapping("/getUsers")
    List<User> mainroute(){
        List<User> result = new ArrayList<>();
        repository.findAll(-1).forEach(user -> result.add(user));
        return result;
    }
    @RequestMapping("/addUser")
    void mainroute(@RequestBody User user){
        repository.save(user);
    }
}
