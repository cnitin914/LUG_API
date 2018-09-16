package com.lug.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.lug.model.User;

@Component
public class UserOperationsImpl implements UserOperations{

	@Autowired
	private MongoOperations mongo;
	

	@Override
	public List<User> findByFirstName(String firstName) {
		Criteria where = Criteria.where("first_name").is(firstName);
		Query query = Query.query(where);
		return mongo.find(query, User.class);
		
	}
	
	@Override
	public List<User> findBySex(String sex) {
		Criteria where = Criteria.where("sex").is(sex);
		Query query = Query.query(where);
		return mongo.find(query, User.class);
		
	}

}
