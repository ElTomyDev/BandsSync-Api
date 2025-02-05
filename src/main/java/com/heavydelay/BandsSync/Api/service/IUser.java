package com.heavydelay.BandsSync.Api.service;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.user.UserRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;

public interface IUser {
    List<UserResponseDto> showAllUsers(boolean detailed);
    UserResponseDto showUserById(Long id, boolean detailed);
    
    void deleteUserById(Long id);

    UserResponseDto registerNewUser(UserRequestDto dto);
    UserResponseDto loginUser(UserRequestDto dto);

    // actualizar valore por id o nombre de usuario
    UserResponseDto updateImageURL(UserRequestDto dto, Long id, String username);
    UserResponseDto updateName(UserRequestDto dto, Long id, String username);
    UserResponseDto updateLastname(UserRequestDto dto, Long id, String username);
    UserResponseDto updateDescription(UserRequestDto dto, Long id, String username);
    UserResponseDto updateStatus(UserRequestDto dto, Long id, String username);
    UserResponseDto updatePhoneNumber(UserRequestDto dto, Long id, String username);
    UserResponseDto updateFindBands(UserRequestDto dto, Long id, String username);


}
