package com.dipo.mobileappwebservice.io.repositories;

import com.dipo.mobileappwebservice.io.entity.AddressEntity;
import com.dipo.mobileappwebservice.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByUserDetails(UserEntity userEntity);
}
