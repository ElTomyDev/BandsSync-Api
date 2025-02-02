package com.heavydelay.BandsSync.Api.model.dto.user.user_password;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.model.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPasswordResponseDto {

    private Long idPassword;

    private User user;
    private String username;

    private String password;

    private String passwordResetToken;

    private String passwordResetExpiry;

    private String createDate;
}
