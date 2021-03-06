package com.dipo.mobileappwebservice.shared.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4865903039190150223L;

    private long id;

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String encryptedPassword;
    private String emailVerificationPassword;
    private boolean emailVerificationStatus = false;
    private List<AddressDto> addresses;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmailVerificationPassword() {
        return emailVerificationPassword;
    }

    public void setEmailVerificationPassword(String emailVerificationPassword) {
        this.emailVerificationPassword = emailVerificationPassword;
    }

    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
