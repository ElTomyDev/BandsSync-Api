package com.heavydelay.BandsSync.Api.model.dto.user;

import com.heavydelay.BandsSync.Api.enums.UserStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/*
    Falta agregar las validaciones personalizadas para el phoneNumber, username, imageUrl y status
    Falta agregar como se va a mostrar los datos a ingresar en el JSON de forma personalizada
*/

public class UserRequestDto {

    // aca faltan las claves foraneas roles, locations, social_links.
    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    private String imageUrl;
    
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    private String lastname;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 50, message = "Username cannot exceed 50 characters and cannot be less than 4 characters")
    private String username;

    @Size(max = 360, message = "Description cannot exceed 360 characters")
    private String description;

    @NotNull(message = "User status cannot be null")
    private UserStatus status;

    @Size(max = 25, message = "Phone number cannot exceed 25 characters")
    private String phoneNumber;

    @NotNull(message = "Find bands field cannot be null")
    private Boolean findBands;

    @NotNull(message = "Failed login attempts cannot be null")
    @Min(value = 0, message = "Failed login attempts cannot be negative")
    private Integer failedLoginAttempts;

}
