package com.toshko.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.toshko.dto.UserDTO;
import com.toshko.entity.User;

public class UserTransformation {

	public static UserDTO transformUserToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setBirthdate(user.getBirthdate().toString());
		userDTO.setEmail(user.getEmail());

		return userDTO;
	}

	public static User trasformUserDtoToUser(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		
		user.setBirthdate(getUserDate(userDTO.getBirthdate()));
		user.setEmail(userDTO.getEmail());

		return user;
	}

	private static LocalDate getUserDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
	}
}
