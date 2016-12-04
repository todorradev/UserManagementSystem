package com.toshko.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.toshko.dto.UserDTO;
import com.toshko.entity.User;
import com.toshko.helper.UserTransformation;
import com.toshko.repository.UserRepository;

@Service
@Component
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void createUser(UserDTO userDTO) {
		User user = UserTransformation.trasformUserDtoToUser(userDTO);
		userRepository.save(user);
	}

	public List<UserDTO> findAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userDTO = new ArrayList<>();
		for (User user : users) {
			userDTO.add(UserTransformation.transformUserToUserDTO(user));
		}

		return userDTO;
	}

	public UserDTO getUser(Long id) {
		User user = userRepository.findOne(id);
		if(user == null)
			return null;

		UserDTO userDTO = UserTransformation.transformUserToUserDTO(user);
		return userDTO;
	}

	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	public UserDTO getUser(String email) {
		Optional<User> tempUser = userRepository.findByEmail(email);
		if(!tempUser.isPresent())
			return null;
		
		User user = tempUser.get();
		
		return UserTransformation.transformUserToUserDTO(user);
	}

	public void updateUser(UserDTO userDTO) {
		User user = UserTransformation.trasformUserDtoToUser(userDTO);
		userRepository.update(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthdate());
	}
}
