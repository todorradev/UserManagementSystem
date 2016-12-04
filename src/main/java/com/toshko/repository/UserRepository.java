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
			+ " set firstName = ?2,"
			+ " lastName = ?3,"
			+ " birthdate = ?4"
			+ " where id = ?1")
	void update(Long id, String firstName, String lastName, LocalDate birthdate);
}
