package com.toshko.resources;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toshko.entity.User;
import com.toshko.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser() throws URISyntaxException {
		User user = new User();
		user.setId(3L);
		user.setFirstName("Todor");
		user.setLastName("Radev");
		user.setEmail("todor.radev@gmail.com");
		LocalDate localDate = LocalDate.now();
		user.setBirthdate(localDate);
		userService.createUser(user);
		return null;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public void getAllUsers() {
		List<User> users = userService.findAllUsers();
		System.out.println(users.toString());
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public void getUser(@PathVariable Long id) {
		User user = userService.getUser(id);
		System.out.println(user);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public void updateUser() {
		System.out.println("update user");
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
