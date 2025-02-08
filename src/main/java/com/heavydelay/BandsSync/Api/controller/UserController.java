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

    // METODOS PUT
    @JsonView(UserRequestDto.NameView.class)
    @PutMapping("/update-name/{username}")
    public ResponseEntity<MessageResponse> updateName(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateName(dto, username);
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
    public ResponseEntity<MessageResponse> updateLastname(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateLastname(dto, username);
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
    public ResponseEntity<MessageResponse> updateDescription(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateDescription(dto, username);
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
    public ResponseEntity<MessageResponse> updateStatus(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateStatus(dto, username);
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
    public ResponseEntity<MessageResponse> updateFindBands(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateFindBands(dto, username);
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
    public ResponseEntity<MessageResponse> updateImageURL(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updateImageURL(dto, username);
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
    public ResponseEntity<MessageResponse> updatePhoneNumber(@RequestBody @Valid UserRequestDto dto, @PathVariable String username) {
        UserResponseDto userUpdate = userService.updatePhoneNumber(dto, username);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The 'phoneNumber' field of user with ID '"+ userUpdate.getIdUser() + "' updated successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse(userUpdate)
            .build(), HttpStatus.OK
        );
    }
}
