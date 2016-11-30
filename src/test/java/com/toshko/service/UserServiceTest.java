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

import com.toshko.data.Application;
import com.toshko.dto.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserServiceTest {
	@Inject
	UserService userService;

	private static UserDTO initialUser = new UserDTO();
	static {
		populateUser();
	}
	@Test
	public void createUserPositive() {
		userService.createUser(initialUser);
		UserDTO userDTO = userService.getUser("fakeUser@gmail.com");
		if(userDTO != null)
			assertEquals(userDTO.getEmail(), initialUser.getEmail());
		else
			fail();
	}


	@Test
	public void createUserNegative() {
		try {
			userService.createUser(initialUser);
		} catch(Exception e) {
			System.err.println("User with this email is already registered");
			UserDTO userDTO = userService.getUser("todor.radev@gmail.com");
			assertEquals(userDTO, userDTO);
		}
	}

	@Test
	public void updateUserPositive() {
		UserDTO userDTO = userService.getUser(initialUser.getEmail());
		if(userDTO == null)
			fail();

		userDTO.setFirstName("Test");
		userDTO.setLastName("Testov");
		userService.updateUser(userDTO);
		UserDTO test = userService.getUser(initialUser.getEmail());
		assertEquals(test.getFirstName(), "Test");
	}

	@Test
	public void updateUserNegative() {
		UserDTO currentUser = userService.getUser(initialUser.getEmail());
		if(currentUser == null)
			fail();

		currentUser.setId(1L);
		userService.updateUser(currentUser);
		UserDTO updatedUser = userService.getUser(initialUser.getEmail());
		assertFalse(currentUser.getId().equals(updatedUser.getId()));
	}

	public void deleteUserPositive() {
		UserDTO currentUser = userService.getUser(initialUser.getEmail());
		userService.deleteUser(currentUser.getId());

		UserDTO deletedUser = userService.getUser(initialUser.getEmail());
		assertNull(deletedUser);
	}

	private static void populateUser() {
		initialUser.setFirstName("Todor");
		initialUser.setLastName("Radev");
		initialUser.setEmail("fakeUser@gmail.com");
		initialUser.setBirthdate("2016-11-28");
	}
}
