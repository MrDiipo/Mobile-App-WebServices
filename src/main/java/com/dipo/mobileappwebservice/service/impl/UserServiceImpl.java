package com.dipo.mobileappwebservice.service.impl;

import com.dipo.mobileappwebservice.exceptions.UserServiceException;
import com.dipo.mobileappwebservice.io.repositories.UserRepository;
import com.dipo.mobileappwebservice.io.entity.UserEntity;
import com.dipo.mobileappwebservice.service.UserService;
import com.dipo.mobileappwebservice.shared.Utils;
import com.dipo.mobileappwebservice.shared.dto.AddressDto;
import com.dipo.mobileappwebservice.shared.dto.UserDto;
import com.dipo.mobileappwebservice.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

//    if (userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exists");

       for (int i=0; i<user.getAddresses().size(); i++){
           AddressDto addressDto = user.getAddresses().get(i);
           addressDto.setUserDto(user);
           addressDto.setAddressId(utils.generateAddressId(30));
           user.getAddresses().set(i, addressDto);
       }

//        BeanUtils.copyProperties(user, userEntity);
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserEntity storedUserDetails = userRepository.save(userEntity);


//        BeanUtils.copyProperties(storedUserDetails, returnValue);
        UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);
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
        if (returnEntity == null) throw new UserServiceException("User with " + userId + " not found");

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
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {

        List<UserDto> userDtos = new ArrayList<>();

        if (page>0){
            page = page-1;
        }

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);

        List<UserEntity> users = usersPage.getContent();

        for (UserEntity userEntity : users){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        return  new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),  new ArrayList<>());
    }
}
