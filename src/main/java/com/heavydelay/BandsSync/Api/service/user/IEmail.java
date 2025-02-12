package com.heavydelay.BandsSync.Api.service.user;

import com.heavydelay.BandsSync.Api.model.entity.User;

public interface IEmail {
    void createEmail(User user, String email);
    void updateEmail(String oldEmail, String newEmail, User user);
}
