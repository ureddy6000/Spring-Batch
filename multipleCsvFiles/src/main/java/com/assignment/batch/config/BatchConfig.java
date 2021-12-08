package com.assignment.batch.config;

import com.assignment.batch.classifier.FileClassifierCompositeWriter;
import com.assignment.batch.classifier.FileClassifierWriter;
import com.assignment.batch.reader.CSVMultiReader;
import com.assignment.batch.processor.PersonItemProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.assignment.batch.model.Student;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {
	@Value("classpath:input/students*.csv")
	private Resource[] resource;

	@Autowired
	private final JobBuilderFactory jobBuilderFactory;

	@Autowired
	private final StepBuilderFactory stepBuilderFactory;

	public Classifier<Student, ItemWriter<? super Student>> itemWriterClassifier() {
		return student -> new FileClassifierWriter().classify(student);
	}

	public Step step1(){
		return stepBuilderFactory.get("step1")
				.<Student, Student>chunk(100)
				.reader(new CSVMultiReader(resource))
				.processor(new PersonItemProcessor())
				.writer(new FileClassifierCompositeWriter(itemWriterClassifier())).build();
	}

	@Bean
	public Job exportPersonJob(){
		return jobBuilderFactory.get("exportPeronJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1()).end().build();
	}
}
