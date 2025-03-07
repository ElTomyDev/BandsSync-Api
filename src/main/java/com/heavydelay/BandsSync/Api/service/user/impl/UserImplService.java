package com.heavydelay.BandsSync.Api.service.user.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.user.location.LocationRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.location.LocationResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.user.user_email.UserEmailRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.user_password.UserPasswordRequestDto;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.mapper.user.IUserMapper;
import com.heavydelay.BandsSync.Api.repository.user.UserRepository;
import com.heavydelay.BandsSync.Api.service.external_data.IRole;
import com.heavydelay.BandsSync.Api.service.external_data.ISocialLinks;
import com.heavydelay.BandsSync.Api.service.user.IEmail;
import com.heavydelay.BandsSync.Api.service.user.ILocation;
import com.heavydelay.BandsSync.Api.service.user.IPassword;
import com.heavydelay.BandsSync.Api.service.user.IUser;

@Service
public class UserImplService implements IUser{
    // FALTA LAS PREFERENCIAS DEL USUARIO Y LA AUTENTICACION PARA EMAIL Y EL LOGIN Y ALGUNAS VALIDACIONES PARA LA CONTRASEÑA
    
    // Repositorios
    private UserRepository userRepository;
    
    //Servicios
    private IEmail emailService;
    private IPassword passwordService;
    private ILocation locationService;
    private ISocialLinks socialService;
    private IRole roleService;

    // Mappeos
    private IUserMapper userMapper;
    
    public UserImplService(UserRepository userRepository, IEmail emailService,
            IPassword passwordService, ILocation locationService, ISocialLinks socialService, IRole roleService,
            IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordService = passwordService;
        this.locationService = locationService;
        this.socialService = socialService;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }

    ////// DELETE ////////////////////////////////////////////////////////
    @Override
    public void deleteUser(String username, Long id) {
        User user = this.findUserByIdOrUsername(username, id);
        
        emailService.deleteEmailByUser(user);
        passwordService.deletePasswordByUser(user);
        userRepository.delete(user);
        locationService.deleteLocationByUser(user);
        socialService.deleteSocialLinksByUser(user);
        // Falta eliminar el user preferences
    }

    ////// REGISTER & LOGIN /////////////////////////////////////////////////
    @Override
    public UserResponseDto loginUser(UserRequestDto dto) {
        return null;
    }

    @Override
    public UserResponseDto registerNewUser(UserRequestDto dto) {

        if(userRepository.findByUsername(dto.getUsername()).orElse(null) != null){
            throw new IllegalArgumentException("The username '" + dto.getUsername() + "' is already in use");
        }

        User user = User.builder()
                    .name(dto.getName())
                    .lastname(dto.getLastname())
                    .username(dto.getUsername())
                    .socialLinks(socialService.createEmptySocialLinks())
                    .location(locationService.createEmptyLocation())
                    .role(roleService.getNoneRole())
                    .build();

        
        // Se guarda el usuario
        userRepository.save(user);

        // Creacion de la contraseña
        passwordService.createPassword(user, dto.getRegisterPassword());
        
        // Creacion de email
        emailService.createEmail(user, dto.getRegisterEmail());
        
        
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
    public UserResponseDto showUser(String username, Long id, boolean detailed) {
        User user = this.findUserByIdOrUsername(username, id);

        return detailed ? userMapper.toDetailedDto(user) : userMapper.toBasicDto(user);
    }

    ////// UPDATE VALUES ////////////////////////////////////////////////////
    @Override
    public UserResponseDto updateRole(UserRequestDto dto, String username, Long id) {
        User user = this.findUserByIdOrUsername(username, id);

        user.setRole(roleService.getRoleByName(dto.getRoleName()));
        userRepository.save(user);
        return userMapper.toBasicDto(user);
    }

    @Override
    public SocialLinksResponseDto updateSocialLinks(SocialLinksRequestDto dto, String username, Long id){
        User user = this.findUserByIdOrUsername(username, id);

        SocialLinksResponseDto socialUpdate = socialService.updateSocialLinksForUser(user, dto);

        return socialUpdate;

    }

    @Override
    public LocationResponseDto updateLocation(LocationRequestDto dto, String username, Long id){
        User user = this.findUserByIdOrUsername(username, id);

        LocationResponseDto locationUpdateDto = locationService.updateLocationForUser(user, dto);
        
        return locationUpdateDto;
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

    @Override
    public UserResponseDto updatePassword(UserPasswordRequestDto dto, String username, Long id){
        User user = this.findUserByIdOrUsername(username, id);

        passwordService.updatePassword(dto.getOldPassword(), dto.getNewPassword(), user);

        return userMapper.toBasicDto(user);

    }

    @Override
    public UserResponseDto updateEmail(UserEmailRequestDto dto, String username, Long id){
        User user = this.findUserByIdOrUsername(username, id);

        emailService.updateEmail(dto.getOldEmail(), dto.getNewEmail(), user);

        return userMapper.toBasicDto(user);

    }

    @Override
    public UserResponseDto updateUsername(UserRequestDto dto, String username, Long id){
        User user = this.findUserByIdOrUsername(username, id);

        if(user.getUsername().equalsIgnoreCase(dto.getUsername())){
            throw new IllegalArgumentException("You are already using that username");
        }

        if(userRepository.findByUsername(dto.getUsername()).orElse(null) != null){
            throw new IllegalArgumentException("The username '" + dto.getUsername() + "' is already in use");
        }
        
        user.setUsername(dto.getUsername());
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
