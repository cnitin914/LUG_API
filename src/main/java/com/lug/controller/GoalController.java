package com.lug.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lug.db.GoalOperations;
import com.lug.model.Goal;

@RestController
@RequestMapping("/goals")
public class GoalController {
	
	@Autowired
	GoalOperations goalOperations;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Goal> getGoals() {
		
		return goalOperations.getGoals();
		
	}
	
	@RequestMapping(value="/{name}", method=RequestMethod.GET)
	public Goal findGoal(@PathVariable String name) {
		
		return goalOperations.findOne(name);
		
	}

}
