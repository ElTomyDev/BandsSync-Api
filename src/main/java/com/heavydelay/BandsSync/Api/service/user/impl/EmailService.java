package com.heavydelay.BandsSync.Api.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.entity.UserEmail;
import com.heavydelay.BandsSync.Api.model.mapper.IUserEmailMapper;
import com.heavydelay.BandsSync.Api.repository.user.UserEmailRepository;
import com.heavydelay.BandsSync.Api.service.user.IEmail;

@Service
public class EmailService implements IEmail{

    @Autowired
    UserEmailRepository emailRepository;
    IUserEmailMapper emailMapper;


    @Override
    public void createEmail(User user, String email) {
        UserEmail userEmail = UserEmail.builder()
                              .user(user)
                              .email(email) 
                              .build();
                              
        emailRepository.save(userEmail);
    }

    @Override
    public void updateEmail(String oldEmail, String newEmail, User user) {

        UserEmail userEmail = emailRepository.findByIdUser(user.getIdUser()).orElseThrow(
            () -> new ResourceNotFoundException("The user password with ID '" + user.getIdUser() + "' not found.")
        );

        if(!userEmail.getEmail().equalsIgnoreCase(oldEmail)){
            throw new IllegalArgumentException("The current email is not correct");
        }

        userEmail.setEmail(newEmail);
        emailRepository.save(userEmail);
    }

}
