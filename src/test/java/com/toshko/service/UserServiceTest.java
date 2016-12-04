package com.toshko.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.toshko.Application;
import com.toshko.dto.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserServiceTest {

	@Inject
	UserService userService;

	private UserDTO initialUser = new UserDTO();

	@Test
	public void createUserPositive() {
		populateUser("createUser@gmail.com");
		userService.createUser(initialUser);
		UserDTO userDTO = userService.getUser("createUser@gmail.com");
		if(userDTO != null)
			assertEquals(userDTO.getEmail(), initialUser.getEmail());
		else
			fail();
	}

	@Test
	public void updateUserPositive() {
		populateUser("updateUserPositive@gmail.com");
		userService.createUser(initialUser);
		UserDTO userDTO = userService.getUser(initialUser.getEmail());
		if(userDTO == null)
			fail();

		userDTO.setFirstName("updatedFirstName");
		userDTO.setLastName("updatedLastName");
		userService.updateUser(userDTO);
		UserDTO updatedUser = userService.getUser(initialUser.getEmail());

		assertEquals(updatedUser.getFirstName(), "updatedFirstName");
		assertEquals(updatedUser.getLastName(), "updatedLastName");
	}

	@Test
	public void updateUserNegative() {
		populateUser("updateUserNegtive@gmail.com");
		userService.createUser(initialUser);
		UserDTO currentUser = userService.getUser(initialUser.getEmail());
		if(currentUser == null)
			fail();

		currentUser.setId(1L);
		userService.updateUser(currentUser);
		UserDTO updatedUser = userService.getUser(initialUser.getEmail());
		assertFalse(currentUser.getId().equals(updatedUser.getId()));
	}

	public void deleteUserPositive() {
		populateUser("deleteUser@gmail.com");
		userService.createUser(initialUser);
		UserDTO currentUser = userService.getUser(initialUser.getEmail());
		userService.deleteUser(currentUser.getId());

		UserDTO deletedUser = userService.getUser(initialUser.getEmail());
		assertNull(deletedUser);
	}

	private void populateUser(String email) {
		initialUser.setFirstName("Todor");
		initialUser.setLastName("Radev");
		initialUser.setBirthdate("2016-11-28");
		initialUser.setEmail(email);
	}
}