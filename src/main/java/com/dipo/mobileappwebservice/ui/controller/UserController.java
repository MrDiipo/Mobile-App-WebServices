package com.dipo.mobileappwebservice.ui.controller;

import com.dipo.mobileappwebservice.exceptions.UserServiceException;
import com.dipo.mobileappwebservice.service.impl.UserService;
import com.dipo.mobileappwebservice.shared.dto.UserDto;
import com.dipo.mobileappwebservice.ui.model.request.UserDetailRequestModel;
import com.dipo.mobileappwebservice.ui.model.response.ErrorMessages;
import com.dipo.mobileappwebservice.ui.model.response.OperationStatusModel;
import com.dipo.mobileappwebservice.ui.model.response.RequestOperationStatus;
import com.dipo.mobileappwebservice.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest getUser(@PathVariable("userId") String userId){

        UserRest userRest = new UserRest();

        UserDto userDto = userService.getUserById(userId);

        BeanUtils.copyProperties(userDto, userRest);

        return userRest;
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public UserRest createUser(@RequestBody UserDetailRequestModel userDetails) throws Exception{

        UserRest returnValue = new UserRest();

        if (userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @PutMapping( path = "/{userId}",
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest updateUser(@PathVariable String userId, @RequestBody UserDetailRequestModel userDetails){
        UserRest returnValue = new UserRest();

        if (userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto updateUser = userService.updateUser(userId, userDto);
        BeanUtils.copyProperties(updateUser, returnValue);

        return returnValue;
    }

    @DeleteMapping( path = "/{userId}",
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public OperationStatusModel deleteUser(@PathVariable String userId){

        OperationStatusModel operationStatusModel = new OperationStatusModel();

        operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());

        userService.deleteUser(userId);
        return operationStatusModel;
    }

}
