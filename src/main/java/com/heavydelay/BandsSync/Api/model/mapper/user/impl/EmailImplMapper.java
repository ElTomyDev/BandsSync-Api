package com.heavydelay.BandsSync.Api.model.mapper.user.impl;

import org.springframework.stereotype.Component;

import com.heavydelay.BandsSync.Api.model.dto.user.user_email.UserEmailResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.UserEmail;
import com.heavydelay.BandsSync.Api.model.mapper.user.IEmailMapper;

@Component
public class EmailImplMapper implements IEmailMapper{

    @Override
    public UserEmailResponseDto toBasicDto(UserEmail userEmail) {
        return UserEmailResponseDto.builder()
               .idEmail(userEmail.getIdEmail())
               .username(userEmail.getUser().getUsername())
               .email(userEmail.getEmail())
               .emailVerified(userEmail.getEmailVerified())
               .emailVerificationToken(userEmail.getEmailVerificationToken())
               .emailVerificationExpiry(userEmail.getEmailVerificationExpiry())
               .build();
    }

    @Override
    public UserEmailResponseDto toDetailedDto(UserEmail userEmail) {
        return UserEmailResponseDto.builder()
               .idEmail(userEmail.getIdEmail())
               .user(userEmail.getUser())
               .email(userEmail.getEmail())
               .emailVerified(userEmail.getEmailVerified())
               .emailVerificationToken(userEmail.getEmailVerificationToken())
               .emailVerificationExpiry(userEmail.getEmailVerificationExpiry())
               .createDate(userEmail.getCreateDate())
               .build();
    }

}
