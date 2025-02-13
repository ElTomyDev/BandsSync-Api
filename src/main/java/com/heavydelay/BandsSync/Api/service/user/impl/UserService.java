package com.heavydelay.BandsSync.Api.service.user.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.external_data.location.LocationRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.location.LocationResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.user.user_email.UserEmailRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.user_password.UserPasswordRequestDto;
import com.heavydelay.BandsSync.Api.model.entity.Location;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.mapper.external_data.ILocationMapper;
import com.heavydelay.BandsSync.Api.model.mapper.external_data.ISocialLinksMapper;
import com.heavydelay.BandsSync.Api.model.mapper.user.IUserMapper;
import com.heavydelay.BandsSync.Api.repository.external_data.LocationRepository;
import com.heavydelay.BandsSync.Api.repository.external_data.RoleRepository;
import com.heavydelay.BandsSync.Api.repository.external_data.SocialLinksRepository;
import com.heavydelay.BandsSync.Api.repository.user.UserRepository;
import com.heavydelay.BandsSync.Api.service.user.IEmail;
import com.heavydelay.BandsSync.Api.service.user.IPassword;
import com.heavydelay.BandsSync.Api.service.user.IUser;

@Service
public class UserService implements IUser{

    // Repositorios
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private SocialLinksRepository socialRepository;
    private LocationRepository locationRepository;
    
    //Servicios
    private IEmail emailService;
    private IPassword passwordService;
    
    // Mappeos
    private IUserMapper userMapper;
    private ISocialLinksMapper socialMapper;
    private ILocationMapper locationMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            SocialLinksRepository socialRepository, LocationRepository locationRepository, IEmail emailService,
            IPassword passwordService, IUserMapper userMapper, ISocialLinksMapper socialMapper,
            ILocationMapper locationMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.socialRepository = socialRepository;
        this.locationRepository = locationRepository;
        this.emailService = emailService;
        this.passwordService = passwordService;
        this.userMapper = userMapper;
        this.socialMapper = socialMapper;
        this.locationMapper = locationMapper;
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

        if(userRepository.findByUsername(dto.getUsername()).orElse(null) != null){
            throw new IllegalArgumentException("The username '" + dto.getUsername() + "' is already in use");
        }

        User user = User.builder()
                    .name(dto.getName())
                    .lastname(dto.getLastname())
                    .username(dto.getUsername())
                    .role(roleRepository.findByRoleName("None").orElseThrow(
                        () -> new ResourceNotFoundException("There is no role with the name 'None'")
                    )).build();
        
        // Para las redes sociales (Crea un objeto vacio)
        SocialLinks social = new SocialLinks();
        socialRepository.save(social);
        user.setSocialLinks(social);

        // Para la localidad (Crea un objeto vacio)
        Location location = new Location();
        locationRepository.save(location);
        user.setLocation(location);

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

        user.setRole(
            roleRepository.findByRoleName(dto.getRoleName()).orElseThrow(
                () -> new ResourceNotFoundException("The role with name '" + dto.getRoleName() + "' was not found")
            )
        );
        userRepository.save(user);
        return userMapper.toBasicDto(user);
    }

    @Override
    public SocialLinksResponseDto updateSocialLinks(SocialLinksRequestDto dto, String username, Long id){
        User user = this.findUserByIdOrUsername(username, id);

        SocialLinks social = socialRepository.findById(user.getSocialLinks().getIdSocial()).orElseThrow(
            () -> new ResourceNotFoundException("The Social links with ID '" + user.getSocialLinks().getIdSocial() + "' was not found")
        );

        social.setInstagram(dto.getInstagram());
        social.setFacebook(dto.getFacebook());
        social.setTwitter(dto.getTwitter());
        social.setTiktok(dto.getTiktok());
        social.setReddit(dto.getReddit());
        social.setYoutube(dto.getYoutube());
        social.setSpotify(dto.getSpotify());
        social.setBandcamp(dto.getBandcamp());
        social.setSoundcloud(dto.getSoundcloud());

        socialRepository.save(social);
        return socialMapper.toBasicDto(social);

    }

    @Override
    public LocationResponseDto updateLocation(LocationRequestDto dto, String username, Long id){
        User user = this.findUserByIdOrUsername(username, id);

        Location location = locationRepository.findById(user.getLocation().getIdLocation()).orElseThrow(
            () -> new ResourceNotFoundException("The location with ID '" + user.getLocation().getIdLocation() + "' was not found")
        );

        locationRepository.save(location);
        return locationMapper.toBasicDto(location);
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
