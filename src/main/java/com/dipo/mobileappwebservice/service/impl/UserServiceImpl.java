package com.dipo.mobileappwebservice.service.impl;

import com.dipo.mobileappwebservice.exceptions.UserServiceException;
import com.dipo.mobileappwebservice.io.repositories.UserRepository;
import com.dipo.mobileappwebservice.io.entity.UserEntity;
import com.dipo.mobileappwebservice.shared.Utils;
import com.dipo.mobileappwebservice.shared.dto.UserDto;
import com.dipo.mobileappwebservice.ui.model.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.generateUserId(30);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(publicUserId);
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserById(String userId) {

        UserDto returnValue = new UserDto();

        UserEntity returnEntity = userRepository.findByUserId(userId);
        if (returnEntity == null) throw new UsernameNotFoundException(userId);

        BeanUtils.copyProperties(returnEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) {
        UserDto returnValue = new UserDto();

        UserEntity returnEntity = userRepository.findByUserId(userId);
        if (returnEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        returnEntity.setFirstName(user.getFirstName());
        returnEntity.setLastName(user.getLastName());
        UserEntity updatedUser = userRepository.save(returnEntity);
        BeanUtils.copyProperties(updatedUser, returnValue);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        return  new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),  new ArrayList<>());
    }
}
