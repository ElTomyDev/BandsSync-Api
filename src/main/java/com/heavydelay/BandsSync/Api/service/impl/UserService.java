package com.heavydelay.BandsSync.Api.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    ////// DELETE ////////////////////////////////////////////////////////
    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The user with ID '" + id + "' not found")
        );

        userRepository.delete(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
            () -> new ResourceNotFoundException("The user with username '" + username + "' not found")
        );

        userRepository.delete(user);
        
    }


    ////// REGISTER & LOGIN /////////////////////////////////////////////////
    @Override
    public UserResponseDto loginUser(UserRequestDto dto) {
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


    ////// SHOW USERS ////////////////////////////////////////////////////////

    @Override
    public List<UserResponseDto> showAllUsers(boolean detailed) {

        List<User> users = (List<User>) userRepository.findAll();

        // Selección del DTO a usar 
        Function<User, UserResponseDto> mapper = detailed ? userMapper::toDetailedDto : userMapper::toBasicDto;

        // Retorno y mapea la lista con todos los usuarios
        return users.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto showUserById(Long id, boolean detailed) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
        );
        return detailed ? userMapper.toDetailedDto(user) : userMapper.toBasicDto(user);
    }

    @Override
    public UserResponseDto showUserByUsername(String username, boolean detailed) {
        User user = userRepository.findByUsername(username).orElseThrow(
            () -> new ResourceNotFoundException("The user with username '" + username + "' not found")
        );

        return detailed ? userMapper.toDetailedDto(user) : userMapper.toBasicDto(user);
    }


    ////// UPDATE VALUES ////////////////////////////////////////////////////
    @Override
    public UserResponseDto updateRole(UserRequestDto dto, String username, Long id) {
        User user = this.findUserByIdOrUsername(username, id);

        user.setRole(
            roleRepository.findByRoleName(dto.getRoleName()).orElseThrow(
                () -> new ResourceNotFoundException("The role with name '" + dto.getRoleName() + "' was not found")
            )
        );
        userRepository.save(user);
        return userMapper.toBasicDto(user);
    }

    @Override
    public UserResponseDto updateDescription(UserRequestDto dto, String username, Long id) {
        User user = this.findUserByIdOrUsername(username, id);

        user.setDescription(dto.getDescription());
        userRepository.save(user);
        return userMapper.toBasicDto(user);
    }

    @Override
    public UserResponseDto updateFindBands(UserRequestDto dto, String username, Long id) {
        User user = this.findUserByIdOrUsername(username, id);

        user.setFindBands(dto.getFindBands());
        userRepository.save(user);
        return userMapper.toBasicDto(user);
    }

    @Override
    public UserResponseDto updateImageURL(UserRequestDto dto, String username, Long id) {
        User user = this.findUserByIdOrUsername(username, id);

        user.setImageURL(dto.getImageURL());
        userRepository.save(user);
        return userMapper.toBasicDto(user);
    }

    @Override
    public UserResponseDto updateLastname(UserRequestDto dto, String username, Long id) {
        User user = this.findUserByIdOrUsername(username, id);

        user.setLastname(dto.getLastname());
        userRepository.save(user);
        return userMapper.toBasicDto(user);
    }

    @Override
    public UserResponseDto updateName(UserRequestDto dto, String username, Long id) {
        User user = this.findUserByIdOrUsername(username, id);

        user.setName(dto.getName());
        userRepository.save(user);
        return userMapper.toBasicDto(user);
    }

    @Override
    public UserResponseDto updatePhoneNumber(UserRequestDto dto, String username, Long id) {
        User user = this.findUserByIdOrUsername(username, id);

        user.setPhoneNumber(dto.getPhoneNumber());
        userRepository.save(user);
        return userMapper.toBasicDto(user);
    }

    @Override
    public UserResponseDto updateStatus(UserRequestDto dto, String username, Long id) {
        User user = this.findUserByIdOrUsername(username, id);
        
        user.setStatus(dto.getStatus());
        userRepository.save(user);
        return userMapper.toBasicDto(user);
    }


    ////// AUXILIARES ////////////////////////////////////////////////////////
    @Override
    public User findUserByIdOrUsername(String username, Long id){
        User user;

        if(username != null){ // si el username no es null
            user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("The user with username '" + username + "' was not found")
            );
        }else if(id != null){ // si el id no es null
            user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The user with ID '" + id + "' was not found")
            );
        }else{ // si el username y el id son null
            throw new IllegalArgumentException("Both username and id cannot be null");
        }

        return user;
    }
    
}
