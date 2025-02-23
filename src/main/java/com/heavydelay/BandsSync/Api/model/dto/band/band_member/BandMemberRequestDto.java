package com.heavydelay.BandsSync.Api.model.dto.band.band_member;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BandMemberRequestDto {

    public interface isAdminView {}
    public interface FindByUserAndBandView {}
    public interface RoleNameView {}
    public interface JoinBandView extends RoleNameView {}

    @JsonView(RoleNameView.class)
    @NotBlank(groups = {RoleNameView.class}, message = "Role name cannot be blank")
    @Size(groups = {RoleNameView.class}, max = 50, message = "Role name cannot exceed 50 characters")
    private String roleName;
    
    @JsonView(JoinBandView.class)
    @NotNull(groups = {JoinBandView.class}, message = "id band field cannot be null")
    private Long idBand;
    
    @JsonView(JoinBandView.class)
    @NotBlank(groups = {JoinBandView.class}, message = "Access Code cannot be blank")
    @Size(groups = {JoinBandView.class}, max = 10, message = "Access Code cannot exceed 10 characters")
    private String accessCode;
    
    @JsonView(FindByUserAndBandView.class)
    @NotBlank(groups = {FindByUserAndBandView.class}, message = "username field cannot be blank")
    private String username;

    @JsonView(FindByUserAndBandView.class)
    @NotBlank(groups = {FindByUserAndBandView.class}, message = "Band name field cannot be blank")
    private String bandName;


}
