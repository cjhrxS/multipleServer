package ua.ita.smartcarservice.service;

import java.util.List;

import ua.ita.smartcarservice.dto.UserDto;
import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.entity.UserEntity;

public interface UserService {

    void createUser(UserEntity userEntity);

    void updateUserById(Long id, UserEntity userEntity);

    UserDto findByUsername(String userName);

    UserDto getUserById(Long id);

    void deleteById (Long id);

    List<UserDto> findAll();

    UserEntity findUser (String userName);

}
