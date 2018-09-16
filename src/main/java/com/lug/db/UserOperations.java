package com.lug.db;

import java.util.List;

import com.lug.model.User;

public interface UserOperations {

	List<User> findByFirstName(String firstName);
	List<User> findBySex(String sex);
}
