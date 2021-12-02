package com.spring.batch.reader;

import com.spring.batch.mapper.PersonRowMapper;
import com.spring.batch.model.Person;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class DatabaseReader extends JdbcCursorItemReader<Person> {

    @Autowired
    private DataSource dataSource;

       public DatabaseReader(DataSource dataSource)
       {
           this.setDataSource(dataSource);
           this.setSql("SELECT person_id,first_name,last_name,email,age FROM person");
           this.setRowMapper(new PersonRowMapper());
       }


}
