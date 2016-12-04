package com.toshko.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.toshko.dto.UserDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	
	private UserDTO userToCreate = new UserDTO();

	private String configUrl;

	@Before
	public void setup() {
		configUrl = "http://localhost:" + port + "/api/users/";
	}

	@Test
	public void createUser() {
		
		populateUser("createUser@gmail.com");

		ResponseEntity<UserDTO> responseEntity = restTemplate.postForEntity(configUrl, userToCreate, UserDTO.class);
		UserDTO returnedUser = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(userToCreate.getEmail(), returnedUser.getEmail());
	}

	@Test
	public void getUser() {
		populateUser("getUser@gmail.com");

		restTemplate.postForEntity(configUrl, userToCreate, UserDTO.class);
		
		ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity(configUrl + userToCreate.getEmail(), UserDTO.class);
		UserDTO returnedUser = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(userToCreate.getEmail(), returnedUser.getEmail());
	}

	@Test
	public void deleteUser() {
		populateUser("delete@gmail.com");

		restTemplate.postForEntity(configUrl, userToCreate, UserDTO.class);
	
		ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity(configUrl + userToCreate.getEmail(), UserDTO.class);
		UserDTO returnedUser = responseEntity.getBody();
		restTemplate.delete(configUrl + returnedUser.getId());
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	private void populateUser(String email) {
		userToCreate.setFirstName("Todor");
		userToCreate.setLastName("Radev");
		userToCreate.setBirthdate("2016-12-04");
		userToCreate.setEmail(email);
	}
}