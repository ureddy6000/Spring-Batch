package com.spring.txt.db.batch;

import com.spring.txt.db.model.User;
import com.spring.txt.db.repository.UserRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DBWriter implements ItemWriter<User> {

    private UserRepository userRepository;

    @Autowired
    public DBWriter (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void write(List<? extends User> users) throws Exception{
        System.out.println("Data Saved for Users: " + users);
        
        List<User> filteredUsers=users.stream()
        		.filter(user->user.getSalary()>14000)
                .collect(Collectors.toList());         		
        		
        
        userRepository.saveAll(filteredUsers);
    }
}
