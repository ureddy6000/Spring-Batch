package com.spring.db.processor;

import org.springframework.batch.item.ItemProcessor;

import com.spring.db.model.Student;

public class Processor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(Student user) throws Exception {
       
        return user;
    }
}
