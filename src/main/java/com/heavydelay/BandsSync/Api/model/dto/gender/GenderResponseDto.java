package com.heavydelay.BandsSync.Api.model.dto.gender;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class GenderResponseDto {

    private Integer idGender;

    private String genderName;
}
