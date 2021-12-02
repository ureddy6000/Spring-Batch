package com.spring.db.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.spring.db.model.Student;
import com.spring.db.processor.Processor;
import com.spring.db.reader.CsvItemReader;
import com.spring.db.writer.DBWriter;

@Configuration
public class SpringBatchConfig {

	//Defining the bean Job with the necessary parameters
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	

	
    @Bean
    public Job job() {

        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }
    
    @Bean
    public Step step() {

    	return stepBuilderFactory.get("ETL-file-load")
                .<Student, Student>chunk(100)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

 
    
   @Bean
   public CsvItemReader itemReader()
   {
	   return new CsvItemReader();
   }
   
   @Bean
   public Processor itemProcessor()
   {
	   return new Processor();
   }
   
   @Bean
   public ItemWriter itemWriter()
   {
	   return new DBWriter();
   }
   
  
    
  
}
