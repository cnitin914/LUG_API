package com.lug.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.lug.model.Goal;

@Profile("mongo")
@Repository
public class MongoGoalRepository implements GoalOperations {

	@Autowired
	MongoOperations mongo;

	@Override
	public List<Goal> getGoals() {
		return mongo.findAll(Goal.class);

	}

	@Override
	public Goal findOne(String goalName) {
		Criteria where = Criteria.where("name").is(goalName);
		Query query = Query.query(where);
		return (Goal) mongo.findOne(query, Goal.class);
	}

	
	

}
