package com.heavydelay.BandsSync.Api.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.entity.UserPassword;
import com.heavydelay.BandsSync.Api.repository.user.UserPasswordRepository;
import com.heavydelay.BandsSync.Api.service.user.IPassword;

public class PasswordService implements IPassword{

    @Autowired
    UserPasswordRepository passwordRepository;

    @Override
    public void createPassword(User user, String password) {
        UserPassword userPassword = UserPassword.builder()
                                    .user(user)
                                    .password(password)
                                    .build();
        passwordRepository.save(userPassword);
    }

    @Override
    public void updatePassword(String oldPassword, String newPasswor, User user) {
        // TODO Auto-generated method stub
        
    }

}
