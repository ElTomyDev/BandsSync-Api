package com.heavydelay.BandsSync.Api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.user.UserRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.entity.UserEmail;
import com.heavydelay.BandsSync.Api.model.entity.UserPassword;
import com.heavydelay.BandsSync.Api.model.mapper.IUserEmailMapper;
import com.heavydelay.BandsSync.Api.model.mapper.IUserMapper;
import com.heavydelay.BandsSync.Api.model.mapper.IUserPasswordMapper;
import com.heavydelay.BandsSync.Api.model.mapper.IUserPreferenceMapper;
import com.heavydelay.BandsSync.Api.repository.external_data.RoleRepository;
import com.heavydelay.BandsSync.Api.repository.user.UserEmailRepository;
import com.heavydelay.BandsSync.Api.repository.user.UserPasswordRepository;
import com.heavydelay.BandsSync.Api.repository.user.UserPreferenceRepository;
import com.heavydelay.BandsSync.Api.repository.user.UserRepository;
import com.heavydelay.BandsSync.Api.service.IUser;

@Service
public class UserService implements IUser{

    // repositorios
    private UserRepository userRepository;
    private UserEmailRepository userEmailRepository;
    private UserPasswordRepository userPasswordRepository;
    private UserPreferenceRepository userPreferenceRepository;
    
    // mappeos
    private IUserMapper userMapper;
    private IUserEmailMapper userEmailMapper;
    private IUserPasswordMapper userPasswordMapper;
    private IUserPreferenceMapper userPreferenceMapper;
    
    //otros
    private RoleRepository roleRepository;


    public UserService(UserRepository userRepository, UserEmailRepository userEmailRepository,
            UserPasswordRepository userPasswordRepository, UserPreferenceRepository userPreferenceRepository,
            IUserMapper userMapper, IUserEmailMapper userEmailMapper, IUserPasswordMapper userPasswordMapper,
            IUserPreferenceMapper userPreferenceMapper, RoleRepository roleRepository) {
        
        this.userRepository = userRepository;
        this.userEmailRepository = userEmailRepository;
        this.userPasswordRepository = userPasswordRepository;
        this.userPreferenceRepository = userPreferenceRepository;

        this.userMapper = userMapper;
        this.userEmailMapper = userEmailMapper;
        this.userPasswordMapper = userPasswordMapper;
        this.userPreferenceMapper = userPreferenceMapper;

        this.roleRepository = roleRepository;
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The user with ID '" + id + "' not found")
        );

        userRepository.delete(user);
    }

    @Override
    public UserResponseDto loginUser(UserRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto registerNewUser(UserRequestDto dto) {
        User user = User.builder()
                    .name(dto.getName())
                    .lastname(dto.getLastname())
                    .username(dto.getUsername())
                    .role(roleRepository.findByRoleName("None").orElseThrow(
                        () -> new ResourceNotFoundException("There is no role with the name 'None'")
                    )).build();
        
        UserPassword password = UserPassword.builder()
                                .user(user)
                                .password(dto.getPassword()).build();
        
        UserEmail email = UserEmail.builder()
                          .user(user)
                          .email(dto.getEmail()).build();
        
        userRepository.save(user);
        userPasswordRepository.save(password);
        userEmailRepository.save(email);

        return userMapper.toBasicDto(user);
    }

    @Override
    public List<UserResponseDto> showAllUsers(boolean detailed) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto showUserById(boolean detailed) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto updateDescription(UserRequestDto dto, Long id, String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto updateFindBands(UserRequestDto dto, Long id, String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto updateImageURL(UserRequestDto dto, Long id, String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto updateLastname(UserRequestDto dto, Long id, String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto updateName(UserRequestDto dto, Long id, String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto updatePhoneNumber(UserRequestDto dto, Long id, String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDto updateStatus(UserRequestDto dto, Long id, String username) {
        // TODO Auto-generated method stub
        return null;
    }

}
