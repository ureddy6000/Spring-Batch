package com.assignment.batch.classifier;

import com.assignment.batch.model.Student;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.classify.Classifier;

public class FileClassifierCompositeWriter extends ClassifierCompositeItemWriter<Student> {
    public FileClassifierCompositeWriter(Classifier<Student, ItemWriter<? super Student>> studentItemWriterClassifier) {
        this.setClassifier(studentItemWriterClassifier);
    }
}
