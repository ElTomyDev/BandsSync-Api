package com.heavydelay.BandsSync.Api.service.user;

import com.heavydelay.BandsSync.Api.model.entity.User;

public interface IPassword {
    void deletePasswordByUser(User user);
    void createPassword(User user, String password);
    void updatePassword(String oldPassword, String newPassword, User user);
}
