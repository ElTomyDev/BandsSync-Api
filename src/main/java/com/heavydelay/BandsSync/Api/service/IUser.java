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
    UserResponseDto updateImageURLByUsername(UserRequestDto dto, String username);
    UserResponseDto updateNameByUsername(UserRequestDto dto, String username);
    UserResponseDto updateLastnameByUsername(UserRequestDto dto, String username);
    UserResponseDto updateDescriptionByUsername(UserRequestDto dto, String username);
    UserResponseDto updateStatusByUsername(UserRequestDto dto, String username);
    UserResponseDto updatePhoneNumberByUsername(UserRequestDto dto, String username);
    UserResponseDto updateFindBandsByUsername(UserRequestDto dto, String username);


}
