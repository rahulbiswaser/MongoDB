package com.fresco.dbrestapi.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoClient extends AbstractMongoClientConfiguration {	
	
	 
	    
	    
	    @Override
	    protected String getDatabaseName() {
	        return "FresoTweet";
	    }
	 
	    @Override
	    public com.mongodb.client.MongoClient mongoClient() {
	        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/FresoTweet");
	        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
	            .applyConnectionString(connectionString)
	            .build();
	        
	        return MongoClients.create(mongoClientSettings);
	    }
	 
	    
	
}
