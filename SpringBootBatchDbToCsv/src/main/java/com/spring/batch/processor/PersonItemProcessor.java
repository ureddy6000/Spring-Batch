package com.spring.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.spring.batch.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonItemProcessor implements ItemProcessor<Person, Person>{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Person process(Person person) throws Exception {

		if(person.getAge()>30)
		{
            logger.info("Person",person.toString());
			return person;
		} else
		{
			logger.info("Person",person.toString());
			return null;
		}
	}
}
