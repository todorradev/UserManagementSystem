package com.toshko.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.toshko.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	private static User user = new User();
	@Before
	public void test() {
		populateUser();
	}

	@Test
	public void testFindByEmail() {
		persistUserIntoEntityManager();
		Optional<User> returnedUser = userRepository.findByEmail("fakeUser@gmail.com");
		if(!returnedUser.isPresent())
			fail();

		assertEquals("fakeUser@gmail.com", returnedUser.get().getEmail());
	}

	@Test
	public void testUpdateUser() {
		persistUserIntoEntityManager();
		user.setFirstName("Ivan");
		user.setLastName("Petrov");
		userRepository.update(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthdate());
		Optional<User> returnedUser = userRepository.findByEmail("fakeUser@gmail.com");
		if(!returnedUser.isPresent())
			fail();

		assertEquals("Ivan", returnedUser.get().getFirstName());
		assertEquals("Petrov", returnedUser.get().getLastName());
	}

	private void persistUserIntoEntityManager() {
		if(entityManager.getId(user) != null)
			entityManager.merge(user);
		else
			entityManager.persist(user);
	}

	private static void populateUser() {
		user.setFirstName("Todor");
		user.setLastName("Radev");
		user.setEmail("fakeUser@gmail.com");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		user.setBirthdate(LocalDate.parse("2016-11-28", formatter));
	}
}