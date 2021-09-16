package com.dipo.mobileappwebservice.service;

import com.dipo.mobileappwebservice.io.entity.AddressEntity;
import com.dipo.mobileappwebservice.io.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository {
    Iterable<AddressEntity> findAllByUserDetails(UserEntity userEntity);
}
