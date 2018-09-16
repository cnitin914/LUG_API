package com.lug.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lug.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

}
