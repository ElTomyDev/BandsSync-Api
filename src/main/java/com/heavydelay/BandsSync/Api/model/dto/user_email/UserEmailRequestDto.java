package com.heavydelay.BandsSync.Api.model.dto.user_email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserEmailRequestDto {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "The email field must be a valid email")
    private String email;

    @NotNull(message = "Email verified must not be empty")
    private Boolean emailVerified;

    @NotBlank(message = "Email verification token cannot be blank")
    @Size(min = 10, max = 255, message = "Email verification token it must have at least 10 characters and a maximum of 255 characters.")
    private String emailVerificationToken;


}
