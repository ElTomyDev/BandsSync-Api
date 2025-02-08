package com.heavydelay.BandsSync.Api.service;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.user.UserRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;

public interface IUser {
    List<UserResponseDto> showAllUsers(boolean detailed);
    UserResponseDto showUserById(Long id, boolean detailed);
    UserResponseDto showUserByUsername(String username, boolean detailed);
    
    void deleteUserById(Long id);
    void deleteUserByUsername(String username);

    UserResponseDto registerNewUser(UserRequestDto dto);
    UserResponseDto loginUser(UserRequestDto dto);

    // actualizar valore por id o nombre de usuario
    UserResponseDto updateRole(UserRequestDto dto, String username);
    UserResponseDto updateImageURL(UserRequestDto dto, String username);
    UserResponseDto updateName(UserRequestDto dto, String username);
    UserResponseDto updateLastname(UserRequestDto dto, String username);
    UserResponseDto updateDescription(UserRequestDto dto, String username);
    UserResponseDto updateStatus(UserRequestDto dto, String username);
    UserResponseDto updatePhoneNumber(UserRequestDto dto, String username);
    UserResponseDto updateFindBands(UserRequestDto dto, String username);


}
