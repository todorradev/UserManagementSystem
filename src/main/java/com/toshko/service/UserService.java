package com.toshko.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.toshko.entity.User;
import com.toshko.repository.UserRepository;

@Service
@Component
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void createUser(User user) {
		userRepository.save(user);
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User getUser(Long id) {
		return userRepository.findOne(id);
	}

	public void deleteUser(Long id) {
		userRepository.delete(id);
	}
}
