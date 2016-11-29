package com.toshko.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.toshko.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	@Modifying
	@Query("update User"
			+ " set firstName = ?1,"
			+ " lastName = ?2,"
			+ " birthdate = ?3"
			+ " where id = ?4")
	void update(String firstName, String lastName, LocalDate birthdate, Long id);
}
