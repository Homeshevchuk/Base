package com.walk;

import com.walk.Entity.User;
import com.walk.Entity.Walk;
import com.walk.QueryResult.UserQueryResult;
import com.walk.QueryResult.UserRequest;
import com.walk.QueryResult.WayAndUser;
import com.walk.QueryResult.SelectWalkResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by Bogdan on 5/13/2016.
 */
@RestController
@ComponentScan

public class Rest {
    @Autowired
    UserRepository repository;
    @Autowired
    WalksRepository walksRepository;
    @RequestMapping("/getUser")
    UserQueryResult mainroute(Principal user){
        Long id = repository.findByName(user.getName()).getId();
        User owner = repository.findOne(id,-1);
        List<UserRequest> list = repository.getRequests(owner.getId());
        for (UserRequest request:list) {
            request.getUser().setSubscribeRequests(null);
            request.getUser().setMyWalks(null);
            request.getUser().setSubscribed(null);
        }
        UserQueryResult result = new UserQueryResult();
        result.setList(list);
        result.setOwner(owner);
        return result;
    }


    @RequestMapping("/Authorized/getAllWalks")
    List<WayAndUser> getAllActive(){
        List<WayAndUser> result = repository.findAllActive();
        return result;
    }

    @RequestMapping("/Authorized/getWalk")
    SelectWalkResult getWalkById(@RequestParam Long id){
        SelectWalkResult walk =  walksRepository.getWalk(id);
        walk.setWalk(walksRepository.findOne(id,-1));
        walk.getWalk().getPath().sort((o1, o2) -> Integer.compare(o1.getPointId(),o2.getPointId()));
        walk.getOwner().setMyWalks(null);
        walk.getOwner().setSubscribed(null);
        for(User subscr:walk.getSubscribers()){
            subscr.setMyWalks(null);
            subscr.setSubscribed(null);
        }
        return walk;
    }

    @RequestMapping(value = "/Authorized/addWalk", method = RequestMethod.POST)
    void addWalk(Principal user, @RequestBody Walk walk){

        Long id = repository.findByName(user.getName()).getId();
        User owner = repository.findOne(id,-1);
        owner.getMyWalks().add(walk);
        repository.save(owner,-1);

    }

    @RequestMapping("/Authorized/addSubscribeRequest")
    void addRequest(Principal user, @RequestParam Long walkId){
       Walk walk =  walksRepository.findOne(walkId,-1);
        Long id = repository.findByName(user.getName()).getId();
        User owner = repository.findOne(id,-1);
        owner.getSubscribeRequests().add(walk);
        repository.save(owner,-1);
    }

    @RequestMapping("/Authorized/acceptSubscripeRequest")
    void acceptRequest(@RequestParam Long userId, @RequestParam Long walkId){
        User user = repository.findOne(userId,-1);
        Walk walk = walksRepository.findOne(walkId,-1);
        user.getSubscribed().add(walk);
        user.getSubscribeRequests().remove(walk);
        if(walksRepository.getWalk(walkId).getSubscribers().size()==walk.getMaxUsers()){
            walk.setCompleted(true);
        }

    }

    @RequestMapping("/Authorized/rejectSubscripeRequest")
    void rejectRequest(@RequestParam Long userId, @RequestParam Long walkId){
        User user = repository.findOne(userId,-1);
        Walk walk = walksRepository.findOne(walkId,-1);
        user.getSubscribeRequests().remove(walk);
    }
}
