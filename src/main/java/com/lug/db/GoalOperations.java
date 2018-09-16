package com.lug.db;

import java.util.List;



import com.lug.model.Goal;


public interface GoalOperations {
	
	public List<Goal> getGoals();
	
	public Goal findOne(String goalName) ;
		
	
}
