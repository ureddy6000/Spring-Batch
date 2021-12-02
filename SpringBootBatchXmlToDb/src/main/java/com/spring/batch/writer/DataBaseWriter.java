package com.spring.batch.writer;

import com.spring.batch.statementsetter.PersonPreparedStatementSetter;
import com.spring.batch.model.Person;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class DataBaseWriter extends JdbcBatchItemWriter<Person> {



    public DataBaseWriter(DataSource dataSource)
    {
        this.setDataSource(dataSource);
        this.setSql("INSERT INTO person(person_id,first_name,last_name,email,age) VALUES(?,?,?,?,?)");
        this.setItemPreparedStatementSetter(new PersonPreparedStatementSetter());
    }


}
