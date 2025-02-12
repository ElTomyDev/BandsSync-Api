package com.heavydelay.BandsSync.Api.model.dto.user.user_email;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserEmailRequestDto {

    public interface UpdateEmailView {}
    public interface OtherValuesView {}

    @JsonView(OtherValuesView.class)
    @NotBlank(groups = {OtherValuesView.class}, message = "Email cannot be blank")
    @Email(groups = {OtherValuesView.class}, message = "The email field must be a valid email")
    private String email;
    
    
    @JsonView(OtherValuesView.class)
    @NotNull(groups = {OtherValuesView.class}, message = "Email verified must not be empty")
    private Boolean emailVerified;
    
    @JsonView(OtherValuesView.class)
    @NotBlank(groups = {OtherValuesView.class}, message = "Email verification token cannot be blank")
    @Size(groups = {OtherValuesView.class}, min = 10, max = 255, message = "Email verification token it must have at least 10 characters and a maximum of 255 characters.")
    private String emailVerificationToken;

    @JsonView(UpdateEmailView.class)
    @NotBlank(groups = {UpdateEmailView.class}, message = "New email cannot be blank")
    private String oldEmail;
    
    @JsonView(UpdateEmailView.class)
    @NotBlank(groups = {UpdateEmailView.class}, message = "New email cannot be blank")
    @Email(groups = {UpdateEmailView.class}, message = "The new email field must be a valid email")
    private String newEmail;

}
