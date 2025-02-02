package com.heavydelay.BandsSync.Api.model.dto.external_data.gender;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenderResponseDto {

    private Integer idGender;

    private String genderName;
}
