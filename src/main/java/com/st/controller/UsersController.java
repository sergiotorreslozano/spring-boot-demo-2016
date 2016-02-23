package com.st.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.st.domain.Users;
import com.st.repository.UsersRepository;

@RestController
public class UsersController {

	@Autowired
	private UsersRepository usersRepository;

	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public List<Users> findAllTicks() {
		return usersRepository.findAll();
	}
	
	@RequestMapping(value = "/api/{name}/users", method = RequestMethod.POST)
	public Users addUser(@PathVariable String name) {
		Users entity = new Users(name);
		return usersRepository.save(entity);
	}
}
