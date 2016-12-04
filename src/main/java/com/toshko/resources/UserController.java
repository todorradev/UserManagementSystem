package com.toshko.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.toshko.dto.UserDTO;
import com.toshko.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	private UserService userService;

	@CrossOrigin
	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
		if(userService.getUser(userDTO.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		userService.createUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUsers() {
		try {
			return new ResponseEntity<List<UserDTO>>(userService.findAllUsers(), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@RequestMapping(value = "/users/{email:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable String email) {
		UserDTO userDTO = userService.getUser(email);
		if(userDTO == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<UserDTO>(userService.getUser(email), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/users", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
		try {
			userService.updateUser(userDTO);
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
