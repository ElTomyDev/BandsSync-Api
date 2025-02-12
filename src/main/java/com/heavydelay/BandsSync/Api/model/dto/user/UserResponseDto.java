package com.heavydelay.BandsSync.Api.model.dto.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.enums.UserStatus;
import com.heavydelay.BandsSync.Api.model.entity.Location;
import com.heavydelay.BandsSync.Api.model.entity.Role;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {

    private Long idUser;

    private Role role;
    private String roleName;
    
    private Location location;

    private SocialLinks socialLinks;

    private String imageURL;

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
