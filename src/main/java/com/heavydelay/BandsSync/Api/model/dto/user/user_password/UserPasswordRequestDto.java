package com.heavydelay.BandsSync.Api.model.dto.user.user_password;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserPasswordRequestDto {

    @NotBlank(message = "Password cannot be blank")
    // falta validacion de contraseña personalizada
    private String password;

    @NotBlank(message = "Password reset token cannot be blank")
    @Size(min = 10, max = 255, message = "Password reset token it must have at least 10 characters and a maximum of 255 characters.")
    private String passwordResetToken;

}
