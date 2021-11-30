package com.spring.txt.db.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.spring.txt.db.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User, User> {

 

    @Override
    public User process(User user) throws Exception {
       
    	user.setTime(new Date());
        return user;
    }
}
