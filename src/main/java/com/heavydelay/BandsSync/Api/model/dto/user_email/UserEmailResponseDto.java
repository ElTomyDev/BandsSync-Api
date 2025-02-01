package com.heavydelay.BandsSync.Api.model.dto.user_email;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.model.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEmailResponseDto {
    private Long idEmail;

    private User user;
    private String username;

    private String email;

    private Boolean emailVerified;

    private String emailVerificationToken;

    private LocalDateTime emailVerificationExpiry;
    
    private LocalDateTime createDate;
}
