package com.kula.work.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import static java.util.Collections.singletonList;

@Configuration
public class MongoConfig  {

    private String database;
    private String host;
    private String port;


    public MongoConfig(@Value("${spring.data.mongodb.database}") String database, @Value("${spring.data.mongodb.host}") String host,@Value("${spring.data.mongodb.port}") String port){
         this.database = database;
         this.host = host;
         this.port = port;
    }



    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(singletonList(new ServerAddress("127.0.0.1", 27017)));
    }



    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(),"test");
        return mongoTemplate;
    }

}
