package ua.ita.smartcarservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.UserDto;
import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void createUser(UserEntity userEntity) {
			userRepository.save(userEntity);
	}

	public List<UserDto> findAll() {
		List<UserDto> userDtos = new ArrayList<>();
		for (UserEntity user : userRepository.findAll()) {
			userDtos.add(getUserDto(user));
		}
		return userDtos;
	}

	public UserDto getUserById(Long id) {
		UserEntity user = userRepository.getUserById(id);
		UserDto carOwnerDto = getUserDto(user);
		return carOwnerDto;
	}

	public UserDto findByUsername(String username) {
		UserEntity user = userRepository.findByUsername(username).get();
		UserDto userDto = getUserDto(user);
		return userDto;
	}

	public UserEntity findUser (String username) {
		UserEntity user = userRepository.findByUsername(username).get();
		return user;
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);

}

	@Override
	public void updateUserById(Long id, UserEntity userEntity) {
				userEntity.setId(id);
		userRepository.save(userEntity);
		
	}


	//for User => UserDto
	public UserDto getUserDto(UserEntity user) {
		UserDto userDto = new UserDto(user.getId(),
				user.getEmail(),
				user.getPassword(),
				user.getFullName(),
				user.getUsername(),
				user.getNumberPhone());
		return userDto;
	}

}
