package com.toshko.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.toshko.dto.UserDTO;
import com.toshko.entity.User;

public class UserTransformation {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Convert user entity to userDTO
	 * @param user - which will be transform
	 * @return userDTO
	 */
	public static UserDTO transformUserToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setBirthdate(user.getBirthdate().toString());
		userDTO.setEmail(user.getEmail());

		return userDTO;
	}

	/**
	 * Convert userDTO to user entity
	 * @param userDTO - which will be transform
	 * @return user
	 */
	public static User trasformUserDtoToUser(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setBirthdate(stringToDate(userDTO.getBirthdate()));
		user.setEmail(userDTO.getEmail());

		return user;
	}

	private static LocalDate stringToDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return LocalDate.parse(date, formatter);
	}
}
