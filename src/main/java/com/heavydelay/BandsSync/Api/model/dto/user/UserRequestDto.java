package com.heavydelay.BandsSync.Api.model.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.heavydelay.BandsSync.Api.enums.UserStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

/*
    Falta agregar las validaciones personalizadas para el phoneNumber, username, imageUrl y status
*/

@Data
@ToString
public class UserRequestDto {


    public interface RoleView {}
    public interface ImageURLView {}
    public interface NameView {}
    public interface LastnameView {}
    public interface UsernameView {}
    public interface DescriptionView {}
    public interface StatusView {}
    public interface PhoneNumberView {}
    public interface FindBandsView {}
    public interface RegisterUserView {}

    @JsonView(RoleView.class)
    @NotBlank(groups = {RoleView.class}, message = "Role name cannot be blank")
    @Size(groups = {RoleView.class}, max = 50, message = "Role name cannot exceed 50 characters")
    private String roleName;
    

    @JsonView(ImageURLView.class)
    @Size(groups = {ImageURLView.class}, max = 255, message = "Image URL cannot exceed 255 characters")
    private String imageURL;
    
    @JsonView({NameView.class, RegisterUserView.class})
    @NotBlank(groups = {NameView.class, RegisterUserView.class}, message = "Name cannot be blank")
    @Size(groups = {NameView.class, RegisterUserView.class}, max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @JsonView({LastnameView.class, RegisterUserView.class})
    @NotBlank(groups = {LastnameView.class, RegisterUserView.class}, message = "Last name cannot be blank")
    @Size(groups = {LastnameView.class, RegisterUserView.class}, max = 100, message = "Last name cannot exceed 100 characters")
    private String lastname;

    @JsonView({UsernameView.class, RegisterUserView.class})
    @NotBlank(groups = {UsernameView.class, RegisterUserView.class}, message = "Username cannot be blank")
    @Size(groups = {UsernameView.class, RegisterUserView.class}, min = 4, max = 50, message = "Username cannot exceed 50 characters and cannot be less than 4 characters")
    private String username;

    @JsonView(DescriptionView.class)
    @Size(groups = {DescriptionView.class}, max = 360, message = "Description cannot exceed 360 characters")
    private String description;

    @JsonView(StatusView.class)
    @NotNull(groups = {StatusView.class}, message = "User status cannot be null")
    private UserStatus status;

    @JsonView(PhoneNumberView.class)
    @Size(groups = {PhoneNumberView.class}, max = 25, message = "Phone number cannot exceed 25 characters")
    private String phoneNumber;

    @JsonView(FindBandsView.class)
    @NotNull(groups = {FindBandsView.class}, message = "Find bands field cannot be null")
    private Boolean findBands;

    //para el registro de usuario
    @JsonView(RegisterUserView.class)
    @NotBlank(groups = {RegisterUserView.class}, message = "Password cannot be blank")
    @Size(groups = {RegisterUserView.class}, min = 6, max = 255, message = "Password cannot exceed 255 characters and cannot be less than 6 characters")
    private String password;

    @JsonView(RegisterUserView.class)
    @NotBlank(groups = {RegisterUserView.class}, message = "Email cannot be blank")
    @Email(groups = {RegisterUserView.class}, message = "It must be a valid email")
    private String email;


}
