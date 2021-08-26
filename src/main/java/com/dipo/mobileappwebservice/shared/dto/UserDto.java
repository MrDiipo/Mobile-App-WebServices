package com.dipo.mobileappwebservice.shared.dto;

import java.io.Serial;
import java.io.Serializable;

public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4865903039190150223L;
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String encryptedPassword;
    private String emailVerificationPassword;
    private boolean emailVerificationStatus = false;

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

    public String getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(String emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }
}
