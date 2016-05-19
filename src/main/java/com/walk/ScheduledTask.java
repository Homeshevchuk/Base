package com.walk;

import com.walk.Entity.User;
import com.walk.Entity.Walk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by Nazar on 15.05.2016.
 */
@ComponentScan
@Component
public class ScheduledTask {
    @Autowired
    WalksRepository repository;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000*60)
    public void reportCurrentTime() {
        List<Walk> walks = repository.findByCompleted(false);
       for(Walk walk:walks) {
           Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
           if (date.after(walk.getStartDate())) {
               walk.setCompleted(true);
               repository.save(walk, -1);
           }
       }
    }
}
