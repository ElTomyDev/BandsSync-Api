package com.heavydelay.BandsSync.Api.service.user.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.entity.UserPassword;
import com.heavydelay.BandsSync.Api.repository.user.UserPasswordRepository;
import com.heavydelay.BandsSync.Api.service.user.IPassword;

@Service
public class PasswordImplService implements IPassword{

    private UserPasswordRepository passwordRepository;
    private BCryptPasswordEncoder encoder;

    

    public PasswordImplService(UserPasswordRepository passwordRepository, BCryptPasswordEncoder encoder) {
        this.passwordRepository = passwordRepository;
        this.encoder = encoder;
    }

    @Override
    public void createPassword(User user, String password) {
        UserPassword userPassword = UserPassword.builder()
                                    .user(user)
                                    .password(encoder.encode(password))
                                    .build();
        passwordRepository.save(userPassword);
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, User user) {

        UserPassword userPassword = passwordRepository.findByUser(user).orElseThrow(
            () -> new ResourceNotFoundException("The user not found.")
        );

        if (!encoder.matches(oldPassword, userPassword.getPassword())){
            throw new IllegalArgumentException("The current password is not correct");
        }

        // actualizo y guardo la nueva contraseña con haseo
        userPassword.setPassword(encoder.encode(newPassword));
        passwordRepository.save(userPassword);
    }

    @Override
    public void deletePasswordByUser(User user){
        UserPassword passwordDelete = passwordRepository.findByUser(user).orElseThrow(
            () -> new ResourceNotFoundException("The user password not found")
        );

        passwordRepository.delete(passwordDelete);
    }

}
