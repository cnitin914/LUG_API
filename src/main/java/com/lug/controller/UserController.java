package com.lug.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lug.db.UserOperationsImpl;
import com.lug.db.UserRepository;
import com.lug.error.LUGException;
import com.lug.model.User;

@RestController
@RequestMapping("/user")
@EnableAspectJAutoProxy
public class UserController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserOperationsImpl userOp;
	
	//@Value("${callInNight}")
	String prefrence;

	@RequestMapping(path = "/count", method = RequestMethod.GET)
	public long countUsers() {
		return userRepo.count();

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> users(@RequestParam(value = "max", defaultValue = "10") long max) {

		List<User> masterList = userRepo.findAll();
		if (masterList.size() > max) {
			masterList = masterList.subList(0, (int) max);
		}

		HttpStatus status = masterList.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		return new ResponseEntity<List<User>>(masterList, status);
	}

	@RequestMapping(value = "/{firstName}", method = RequestMethod.GET)
	public ResponseEntity<?> users(@PathVariable String firstName) {

		System.out.println(firstName);
		System.out.println("Prefrence " + prefrence);
		List<User> users = userOp.findByFirstName(firstName);
		if (users.size() == 0) {
			return new ResponseEntity<Error>(new Error("User with name " + firstName + " is not found."),
					HttpStatus.NOT_FOUND);
		} else {

			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public List<User> getUserBySex(@RequestParam(value="sex")  String sex) throws LUGException {
		
		List<User> users = userOp.findBySex(sex);
		
		if(users.size()==0) {
			throw new LUGException("1001","Cannot find anything for the sex " + sex);
		} 
		return users;
	}
	
	@ExceptionHandler(LUGException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private Error exceptionHandler(LUGException ex) {
		return new Error(ex.getMessage());
	}
	
	/*
	 * @RequestMapping(value="/{id}",method=RequestMethod.GET) public User
	 * userById(@PathVariable long id) {
	 * 
	 * System.out.println("Hello000000000 " + id); User user =
	 * userRepo.findOne(Long.toString(id)); System.out.println(user); return user;
	 * 
	 * }
	 */

}
