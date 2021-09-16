package com.dipo.mobileappwebservice.service.impl;

import com.dipo.mobileappwebservice.io.entity.AddressEntity;
import com.dipo.mobileappwebservice.io.entity.UserEntity;
import com.dipo.mobileappwebservice.io.repositories.UserRepository;
import com.dipo.mobileappwebservice.service.AddressRepository;
import com.dipo.mobileappwebservice.service.AddressService;
import com.dipo.mobileappwebservice.shared.dto.AddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAddresses(String userId) {

        ModelMapper modelMapper = new ModelMapper();
        List<AddressDto> returnValue = new ArrayList<>();

        UserEntity userEntity = userRepository.findByUserId(userId);

        Iterable<AddressEntity> addressEntities = addressRepository.findAllByUserDetails(userEntity);

        for (AddressEntity addressEntity : addressEntities) {
            returnValue.add(modelMapper.map(addressEntity, AddressDto.class));
        }

        return returnValue;
    }
}
