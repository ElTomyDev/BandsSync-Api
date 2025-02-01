package com.heavydelay.BandsSync.Api.model.dto.band;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.model.entity.Gender;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BandResponseDto {
    private Long idBand;

    private Gender gender;
    private String genderName;
    
    private SocialLinks socialLinks;

    private String imageURL;

    private String bandName;

    private String accessCode;

    private Boolean isSolist;

    private Boolean findMembers;

    private String biography;

    private LocalDateTime createDate;
}
