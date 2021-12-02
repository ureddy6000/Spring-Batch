package com.spring.db.writer;

import com.spring.db.model.Student;
import com.spring.db.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DBWriter implements ItemWriter<Student> {

    @Autowired
    private UserRepository userRepository;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void write(List<? extends Student> users) throws Exception{
        System.out.println("Data Saved for Users: " + users);
        
        List<Student> filteredUsers=users.stream()
        		.filter(user->user.getSalary()>12000)
                .collect(Collectors.toList());         		
        		
        
        try {
			userRepository.saveAll(filteredUsers);
		} catch (Exception e) {
			logger.error("Exception Occurred: "+e.getMessage(),e);
		}
    }
}
