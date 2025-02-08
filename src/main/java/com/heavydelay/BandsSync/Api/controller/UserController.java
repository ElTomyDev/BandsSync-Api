package com.heavydelay.BandsSync.Api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.heavydelay.BandsSync.Api.model.dto.external_data.location.LocationRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.location.LocationResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.user.UserResponseDto;
import com.heavydelay.BandsSync.Api.model.payload.MessageResponse;
import com.heavydelay.BandsSync.Api.service.IUser;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUser userService;

    ////// GET METHOD ///////////////////////////////////////////////////
    @GetMapping("/users/{detailed}")
    public ResponseEntity<MessageResponse> showAllUsers(@PathVariable boolean detailed) {
        List<UserResponseDto> users = userService.showAllUsers(detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Users successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(users)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-user/{id}/{detailed}")
    public ResponseEntity<MessageResponse> showUser(@PathVariable Long id, @PathVariable boolean detailed) {
        UserResponseDto user = userService.showUserById(id, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(user)
            .build(), HttpStatus.OK
        );
    }
    
    ////// POST METHOD ///////////////////////////////////////////////////
    @JsonView(UserRequestDto.RegisterUserView.class)
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody @Valid UserRequestDto dto) {
        UserResponseDto userRegister = userService.registerNewUser(dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User registered successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(userRegister)
            .build(), HttpStatus.CREATED
        );
    }

    ////// DELETE METHOD ///////////////////////////////////////////////////
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User with ID '" + id + "' deleted successfully")
            .status(HttpStatus.OK.value())
            .objectResponse("ID: "+ id)
            .build(), HttpStatus.OK
        );
    }

    @DeleteMapping("/{username}/delete")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User with username '" + username + "' deleted successfully")
            .status(HttpStatus.OK.value())
            .objectResponse("Username: "+ username)
            .build(), HttpStatus.OK
        );
    }

    ////// PUT METHOD BY USERNAME///////////////////////////////////////////////////
    @JsonView(UserRequestDto.RoleView.class)
    @PutMapping("/update-role/{username}")
    public ResponseEntity<MessageResponse> updateRoleByUsername(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateRole(dto, username, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The 'role' field of user with ID '"+ userUpdate.getIdUser() + "' updated successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse(userUpdate)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(SocialLinksRequestDto.SocialLinksView.class)
    @PutMapping("/update-social-links/{username}")
    public ResponseEntity<MessageResponse> updateSocialLinksByUsername(@RequestBody @Valid SocialLinksRequestDto dto, @PathVariable String username) {
        SocialLinksResponseDto socialUpdate = userService.updateSocialLinks(dto, username, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The 'social link' field of user with ID '"+ socialUpdate.getIdSocial() + "' updated successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse(socialUpdate)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(LocationRequestDto.LocationView.class)
    @PutMapping("/update-location/{username}")
    public ResponseEntity<MessageResponse> updateLocationByUsername(@RequestBody @Valid LocationRequestDto dto, @PathVariable String username) {
        LocationResponseDto locationUpdate = userService.updateLocation(dto, username, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The 'Location' field of user with ID '"+ locationUpdate.getIdLocation() + "' updated successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse(locationUpdate)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(UserRequestDto.NameView.class)
    @PutMapping("/update-name/{username}")
    public ResponseEntity<MessageResponse> updateNameByUsername(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateName(dto, username, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The 'name' field of user with ID '"+ userUpdate.getIdUser() + "' updated successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse(userUpdate)
            .build(), HttpStatus.OK
        );
    }
    
    @JsonView(UserRequestDto.LastnameView.class)
    @PutMapping("/update-lastname/{username}")
    public ResponseEntity<MessageResponse> updateLastnameByUsername(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateLastname(dto, username, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The 'lastname' field of user with ID '"+ userUpdate.getIdUser() + "' updated successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse(userUpdate)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(UserRequestDto.DescriptionView.class)
    @PutMapping("/update-description/{username}")
    public ResponseEntity<MessageResponse> updateDescriptionByUsername(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateDescription(dto, username, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The 'description' field of user with ID '"+ userUpdate.getIdUser() + "' updated successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse(userUpdate)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(UserRequestDto.StatusView.class)
    @PutMapping("/update-status/{username}")
    public ResponseEntity<MessageResponse> updateStatusByUsername(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateStatus(dto, username, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The 'status' field of user with ID '"+ userUpdate.getIdUser() + "' updated successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse(userUpdate)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(UserRequestDto.FindBandsView.class)
    @PutMapping("/update-find-bands/{username}")
    public ResponseEntity<MessageResponse> updateFindBandsByUsername(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateFindBands(dto, username, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The 'findBands' field of user with ID '"+ userUpdate.getIdUser() + "' updated successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse(userUpdate)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(UserRequestDto.ImageURLView.class)
    @PutMapping("/update-image-url/{username}")
    public ResponseEntity<MessageResponse> updateImageURLByUsername(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateImageURL(dto, username, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The 'imageURL' field of user with ID '"+ userUpdate.getIdUser() + "' updated successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse(userUpdate)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(UserRequestDto.PhoneNumberView.class)
    @PutMapping("/update-phone-number/{username}")
    public ResponseEntity<MessageResponse> updatePhoneNumberByUsername(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updatePhoneNumber(dto, username, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The 'phoneNumber' field of user with ID '"+ userUpdate.getIdUser() + "' updated successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse(userUpdate)
            .build(), HttpStatus.OK
        );
    }

    ////// PUT METHOD BY ID ///////////////////////////////////////////////////
    
}
