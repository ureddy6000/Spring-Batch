package com.spring.txt.db.controller;

import org.springframework.batch.core.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.txt.db.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;
    
    @Autowired
    private UserRepository userRepo;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping
    public BatchStatus load() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException  {
    	 JobExecution jobExecution=null;

    	
    		Map<String, JobParameter> maps = new HashMap<>();
            maps.put("time", new JobParameter(System.currentTimeMillis()));
            JobParameters parameters = new JobParameters(maps);
            jobExecution = jobLauncher.run(job, parameters);

            logger.info("JobExecution: " + jobExecution.getStatus());
            logger.info("Batch is Running...");
            
            logger.info("The records that are written into database are",userRepo.findAll());
            
            while (jobExecution.isRunning()) {
                System.out.println("...");
                
                
            }
    	
    
		
    	return jobExecution.getStatus();
    	
    	
    	
        
    }
}
