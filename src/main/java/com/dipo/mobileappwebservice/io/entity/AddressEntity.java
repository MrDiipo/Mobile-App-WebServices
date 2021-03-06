package com.dipo.mobileappwebservice.io.entity;

import com.dipo.mobileappwebservice.shared.Utils;
import com.dipo.mobileappwebservice.shared.dto.UserDto;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "addresses")
public class AddressEntity implements Serializable{

    @Serial
    private static final long serialVersionUID = -5686807838041933637L;

    @GeneratedValue
    @javax.persistence.Id
    private long id;

    @Column(length = 30, nullable = false)
    private String addressId;

    @Column(length = 15, nullable = false)
    private String city;

    @Column(length = 15, nullable = false)
    private String country;

    @Column(length = 100, nullable = false)
    private String streetName;

    @Column(length = 7, nullable = false)
    private String postalCode;

    @Column(length = 10, nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity userDto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        Utils utils = new Utils();
        addressId = utils.generateAddressId(30);
        this.addressId = addressId;
    }

    public UserEntity getUserDto() {
        return userDto;
    }

    public void setUserDto(UserEntity userDto) {
        this.userDto = userDto;
    }
}
