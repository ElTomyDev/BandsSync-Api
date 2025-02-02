package com.heavydelay.BandsSync.Api.model.dto.external_data.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LocationRequestDto {

    @NotBlank(message = "Country cannot be blank")
    @Size(min = 3, max = 100, message = "Country cannot exceed 100 characters and cannot be less than 3 characters")
    private String country;

    @NotBlank(message = "State cannot be blank")
    @Size(min = 2, max = 100, message = "State cannot exceed 100 characters and cannot be less than 2 characters")
    private String state;

    @NotBlank(message = "City cannot be blank")
    @Size(min = 2, max = 100, message = "City cannot exceed 100 characters and cannot be less than 2 characters")
    private String city;

    @NotBlank(message = "Postal code cannot be blank")
    @Size(min = 4, max = 20, message = "Postal code cannot exceed 20 characters and cannot be less than 4 characters")
    private String postalCode;

    @NotBlank(message = "Addres cannot be blank")
    @Size(min = 5, max = 255, message = "Addres cannot exceed 255 characters and cannot be less than 5 characters")
    private String address;
    
}
