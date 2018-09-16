package com.lug.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class Goal {
	@Field("name")
	private String name;
	
	public Goal(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
