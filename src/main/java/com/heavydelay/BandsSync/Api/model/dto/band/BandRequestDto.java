package com.heavydelay.BandsSync.Api.model.dto.band;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BandRequestDto {
    
    public interface GenderNameView {}
    public interface ImageURLView {}
    public interface IsSolistView {}
    public interface FindMembersView {}
    public interface BiographyView {}
    public interface BandNameView {}
    public interface CreateBandView {}

    @JsonView({CreateBandView.class, GenderNameView.class})
    @NotBlank(groups = {CreateBandView.class, GenderNameView.class}, message = "Gender name cannot be blank")
    @Size(groups = {CreateBandView.class, GenderNameView.class}, max = 60, message = "Gender name cannot exceed 60 characters")
    private String genderName;
    
    @JsonView({ImageURLView.class})
    @NotBlank(groups = {ImageURLView.class}, message = "Image URL cannot be blank")
    @Size(groups = {ImageURLView.class}, max = 250, message = "Image URL cannot exceed 250 characters")
    private String imageURL;
    
    @JsonView({CreateBandView.class, BandNameView.class})
    @NotBlank(groups = {CreateBandView.class, BandNameView.class}, message = "Band name cannot be blank")
    @Size(groups = {CreateBandView.class, BandNameView.class}, max = 100, message = "Band name cannot exceed 100 characters")
    private String bandName;
    
    @JsonView({CreateBandView.class, IsSolistView.class})
    @NotNull(groups = {CreateBandView.class, IsSolistView.class}, message = "Is solist field cannot be null")
    private Boolean isSolist;
    
    @JsonView({FindMembersView.class})
    @NotNull(groups = {FindMembersView.class}, message = "Find members field cannot be null")
    private Boolean findMembers;
    
    @JsonView({BiographyView.class})
    @NotBlank(groups = {BiographyView.class}, message = "Biography cannot be blank")
    @Size(groups = {BiographyView.class}, max = 500, message = "Biography cannot exceed 500 characters")
    private String biography;

}
