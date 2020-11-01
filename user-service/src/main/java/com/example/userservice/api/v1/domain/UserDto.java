package com.example.userservice.api.v1.domain;


import com.fasterxml.jackson.annotation.JsonFilter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@JsonFilter("BeanFilter")
public class UserDto implements Serializable {


    private static final long serialVersionUID = 7688098672598024756L;

    private Long id;

    @NotNull(message = "First name cannot be empty")
    @Size(min = 2, max = 25, message = "First Name cannot be less than 2 characters and it cannot be greater than 25")
    private String firstName;

    @NotNull(message = "Last name cannot be empty")
    @Size(min = 2, max = 25, message = "Last Name cannot be less than 2 characters and it cannot be greater than 25")
    private String lastName;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, max = 16, message = "Password cannot be less than 2 characters and it cannot be greater than 16")
    private String password;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Must be a proper email")
    private String email;

    private AlbumDtoList albums;

    public UserDto() {
    }

    public UserDto(Long id, @NotNull(message = "First name cannot be empty") @Size(min = 2, max = 25,
            message = "First Name cannot be less than 2 characters and it cannot be greater than 25") String firstName,
                   @NotNull(message = "Last name cannot be empty") @Size(min = 2, max = 25, message = "Last Name cannot be less than 2 characters and it cannot be greater than 25") String lastName,
                   @NotNull(message = "Password cannot be empty") @Size(min = 8, max = 16, message = "Password cannot be less than 2 characters and it cannot be greater than 16") String password,
                   @NotNull(message = "Email cannot be empty") @Email(message = "Must be a proper email") String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public UserDto(Long id,
                   @NotNull(message = "First name cannot be empty") @Size(min = 2, max = 25, message = "First Name cannot be less than 2 characters and it cannot be greater than 25") String firstName,
                   @NotNull(message = "Last name cannot be empty") @Size(min = 2, max = 25, message = "Last Name cannot be less than 2 characters and it cannot be greater than 25") String lastName,
                   @NotNull(message = "Password cannot be empty") @Size(min = 8, max = 16, message = "Password cannot be less than 2 characters and it cannot be greater than 16") String password,
                   @NotNull(message = "Email cannot be empty") @Email(message = "Must be a proper email") String email,
                   AlbumDtoList albums) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.albums = albums;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AlbumDtoList getAlbums() {
        return albums;
    }

    public void setAlbums(AlbumDtoList albums) {
        this.albums = albums;
    }

}
