package com.heavydelay.BandsSync.Api.service;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.external_data.location.LocationRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.location.LocationResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.user.user_password.UserPasswordRequestDto;
import com.heavydelay.BandsSync.Api.model.entity.User;

public interface IUser {
    List<UserResponseDto> showAllUsers(boolean detailed);
    UserResponseDto showUserById(Long id, boolean detailed);
    UserResponseDto showUserByUsername(String username, boolean detailed);
    
    void deleteUserById(Long id);
    void deleteUserByUsername(String username);

    UserResponseDto registerNewUser(UserRequestDto dto);
    UserResponseDto loginUser(UserRequestDto dto);

    // actualizar valore por id o nombre de usuario
    UserResponseDto updateRole(UserRequestDto dto, String username, Long id);
    SocialLinksResponseDto updateSocialLinks(SocialLinksRequestDto dto, String username, Long id);
    LocationResponseDto updateLocation(LocationRequestDto dto, String username, Long id);
    UserResponseDto updateImageURL(UserRequestDto dto, String username, Long id);
    UserResponseDto updateName(UserRequestDto dto, String username, Long id);
    UserResponseDto updateLastname(UserRequestDto dto, String username, Long id);
    UserResponseDto updateDescription(UserRequestDto dto, String username, Long id);
    UserResponseDto updateStatus(UserRequestDto dto, String username, Long id);
    UserResponseDto updatePhoneNumber(UserRequestDto dto, String username, Long id);
    UserResponseDto updateFindBands(UserRequestDto dto, String username, Long id);
    UserResponseDto updatePassword(UserPasswordRequestDto dto, String username, Long id);
    
    // Funciones auxiliares
    User findUserByIdOrUsername(String username, Long id);

}
