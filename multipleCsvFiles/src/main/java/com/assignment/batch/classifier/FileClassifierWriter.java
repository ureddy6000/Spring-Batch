package com.assignment.batch.classifier;

import com.assignment.batch.model.Student;
import com.assignment.batch.writer.CSVWriter;
import lombok.SneakyThrows;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

public class FileClassifierWriter implements Classifier<Student, ItemWriter<? super Student>> {
    
    @Override
    public ItemWriter<? super Student> classify(Student student) {
        String fileName;
        if(student.getPercentage() >= 50){
            fileName = "output/students_success";
        }else{
            fileName = "output/students_rejected";
        }
        return new CSVWriter(fileName);
    }
}
