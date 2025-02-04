package com.heavydelay.BandsSync.Api.service;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;

public interface IUser {
    List<UserResponseDto> showAllUsers(boolean detailed);
    UserResponseDto showUserById(boolean detailed);
    

}
