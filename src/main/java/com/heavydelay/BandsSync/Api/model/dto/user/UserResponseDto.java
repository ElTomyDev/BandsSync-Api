package com.heavydelay.BandsSync.Api.model.dto.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.enums.UserStatus;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private Long idUser;

    // aca faltan las claves foraneas roles, locations, social_links.

    private String imageUrl;

    private String name;

    private String lastname;

    private String username;

    private String description;

    private UserStatus status;

    private String phoneNumber;

    private Boolean findBands;
    
    private LocalDateTime lastFailedLogin;

    private Integer failedLoginAttempts;

    private LocalDateTime lastConnection;

    private LocalDateTime createDate;
}
