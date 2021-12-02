package com.spring.batch.reader;

import com.spring.batch.model.Person;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

public class XMLFileReader extends StaxEventItemReader<Person> {

   public XMLFileReader()
   {
       this.setResource(new ClassPathResource("persons.xml"));
       this.setFragmentRootElementName("person");

       Map<String,String> aliasesMap =new HashMap<String,String>();
       aliasesMap.put("person", "com.spring.batch.model.Person");
       XStreamMarshaller marshaller = new XStreamMarshaller();
       marshaller.setAliases(aliasesMap);

       this.setUnmarshaller(marshaller);
   }
}
