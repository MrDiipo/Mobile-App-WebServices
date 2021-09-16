package com.dipo.mobileappwebservice.service.impl;

import com.dipo.mobileappwebservice.shared.dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAddresses(String userId);
}
