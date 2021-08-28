package com.dipo.mobileappwebservice.service.impl;

import com.dipo.mobileappwebservice.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
    UserDto getUser(String email);
}
