package com.heavydelay.BandsSync.Api.model.dto.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.heavydelay.BandsSync.Api.enums.UserStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

/*
    Falta agregar las validaciones personalizadas para el phoneNumber, username, imageUrl y status
    Falta agregar como se va a mostrar los datos a ingresar en el JSON de forma personalizada
*/

@Data
@ToString
public class UserRequestDto {

    public interface RegisterView {}
    public interface OtherView {}

    // aca faltan las claves foraneas roles, locations, social_links.
    @JsonView(OtherView.class)
    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    private String imageURL;
    
    @JsonView(RegisterView.class)
    @NotBlank(groups = {RegisterView.class}, message = "Name cannot be blank")
    @Size(groups = {RegisterView.class}, max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @JsonView(RegisterView.class)
    @NotBlank(groups = {RegisterView.class}, message = "Last name cannot be blank")
    @Size(groups = {RegisterView.class}, max = 100, message = "Last name cannot exceed 100 characters")
    private String lastname;

    @JsonView(RegisterView.class)
    @NotBlank(groups = {RegisterView.class}, message = "Username cannot be blank")
    @Size(groups = {RegisterView.class}, min = 4, max = 50, message = "Username cannot exceed 50 characters and cannot be less than 4 characters")
    private String username;

    @JsonView(OtherView.class)
    @Size(max = 360, message = "Description cannot exceed 360 characters")
    private String description;

    @JsonView(OtherView.class)
    @NotNull(message = "User status cannot be null")
    private UserStatus status;

    @JsonView(OtherView.class)
    @Size(max = 25, message = "Phone number cannot exceed 25 characters")
    private String phoneNumber;

    @JsonView(OtherView.class)
    @NotNull(message = "Find bands field cannot be null")
    private Boolean findBands;

    @JsonView(OtherView.class)
    @NotNull(message = "Failed login attempts cannot be null")
    @Min(value = 0, message = "Failed login attempts cannot be negative")
    private Integer failedLoginAttempts;

    @JsonView(RegisterView.class)
    @NotBlank(groups = {RegisterView.class}, message = "Password cannot be blank")
    @Size(groups = {RegisterView.class}, min = 6, max = 255, message = "Password cannot exceed 255 characters and cannot be less than 6 characters")
    private String password;

    @JsonView(RegisterView.class)
    @NotBlank(groups = {RegisterView.class}, message = "Email cannot be blank")
    @Email(groups = {RegisterView.class}, message = "It must be a valid email")
    private String email;


}
