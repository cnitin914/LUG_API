package com.lug.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages="com.lug.db")
public class MongoConfig extends AbstractMongoConfiguration{

	@Override
	protected String getDatabaseName() {
		return "LUG";
	}

	@Override
	public Mongo mongo() throws Exception {
		MongoCredential crd = MongoCredential.createCredential("admin","LUG","admin".toCharArray());
		return new MongoClient(new ServerAddress("localhost",27017), Arrays.asList(crd));
	}
	
	/*@Bean
	public MongoFactoryBean mongo() {
		MongoFactoryBean mongo = new MongoFactoryBean();
		mongo.setHost("localhost");
		mongo.setPort(27017);
		return mongo;
	}
	
	@Bean
	public MongoOperations mongotemplate(Mongo mongo) {
		return new MongoTemplate(mongo,"LUG");
	}*/

}
