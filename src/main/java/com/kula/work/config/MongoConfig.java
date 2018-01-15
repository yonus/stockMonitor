package com.kula.work.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;

@Configuration
public class MongoConfig {

    private String database;
    private String host;
    private String port;


    public MongoConfig(@Value("${spring.data.mongodb.database}") String database, @Value("${spring.data.mongodb.host}") String host,@Value("${spring.data.mongodb.port}") String port){
         this.database = database;
         this.host = host;
         this.port = port;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(this.host);
        mongo.setPort(Integer.valueOf(12034));
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, this.database);
        System.out.print("Embedded mongo is up");
        return mongoTemplate;


    }

}
