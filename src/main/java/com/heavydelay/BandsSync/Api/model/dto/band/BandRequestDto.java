package com.heavydelay.BandsSync.Api.model.dto.band;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BandRequestDto {
    
    public interface CreateBand {}

    @JsonView({CreateBand.class})
    @NotBlank(message = "Gender name cannot be blank")
    @Size(max = 60, message = "Gender name cannot exceed 60 characters")
    private String genderName;
    
    @NotBlank(message = "Image URL cannot be blank")
    @Size(max = 250, message = "Image URL cannot exceed 250 characters")
    private String imageURL;
    
    @JsonView({CreateBand.class})
    @NotBlank(message = "Band name cannot be blank")
    @Size(max = 100, message = "Band name cannot exceed 100 characters")
    private String bandName;
    
    @JsonView({CreateBand.class})
    @NotNull(message = "Is solist field cannot be null")
    private Boolean isSolist;
    
    @NotNull(message = "Find members field cannot be null")
    private Boolean findMembers;
    
    @NotBlank(message = "Biography cannot be blank")
    @Size(max = 500, message = "Biography cannot exceed 500 characters")
    private String biography;

}
