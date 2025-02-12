package com.heavydelay.BandsSync.Api.model.dto.user.user_password;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPasswordRequestDto {

    public interface UpdatePasswordView {}
    public interface OtherValuesView {}

    @JsonView(OtherValuesView.class)
    @NotBlank(groups = {OtherValuesView.class}, message = "Password cannot be blank")
    // falta validacion de contraseña personalizada
    private String password;
    
    @JsonView(OtherValuesView.class)
    @NotBlank(groups = {OtherValuesView.class}, message = "Password reset token cannot be blank")
    @Size(groups = {OtherValuesView.class}, min = 10, max = 255, message = "Password reset token it must have at least 10 characters and a maximum of 255 characters.")
    private String passwordResetToken;
    
    @JsonView(UpdatePasswordView.class)
    @NotBlank(groups = {UpdatePasswordView.class}, message = "Old password cannot be blank")
    // falta validacion de contraseña personalizada
    private String oldPassword;
    
    @JsonView(UpdatePasswordView.class)
    @NotBlank(groups = {UpdatePasswordView.class}, message = "New password cannot be blank")
    private String newPassword;

}
