package com.spring.batch.config;

import com.spring.batch.model.Person;
import com.spring.batch.processor.PersonItemProcessor;
import com.spring.batch.reader.DatabaseReader;
import com.spring.batch.writer.CSVFileItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;

	@Bean
	public DatabaseReader dbReader()
	{
		return new DatabaseReader(dataSource);
	}

	@Bean
	public PersonItemProcessor processor(){
		return new PersonItemProcessor();
	}

	@Bean
	public CSVFileItemWriter csvWriter()
	{
		return new CSVFileItemWriter();
	}

	@Bean
	public Step step1(){
		return stepBuilderFactory.get("step1").<Person,Person>chunk(100)
				.reader(dbReader()).
				processor(processor())
				.writer(csvWriter())
				.build();
	}

	@Bean
	public Job exportPersonJob(){
		return jobBuilderFactory.get("exportPeronJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}
}
