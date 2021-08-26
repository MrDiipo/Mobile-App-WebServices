package com.dipo.mobileappwebservice.service.impl;

import com.dipo.mobileappwebservice.UserRepository;
import com.dipo.mobileappwebservice.io.entity.UserEntity;
import com.dipo.mobileappwebservice.shared.Utils;
import com.dipo.mobileappwebservice.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;


    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.generateUserId(30);
        userEntity.setEncryptedPassword("test");
        userEntity.setUserId(publicUserId);
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }
}
