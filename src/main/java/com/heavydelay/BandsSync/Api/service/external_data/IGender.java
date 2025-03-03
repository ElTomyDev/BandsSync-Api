package com.heavydelay.BandsSync.Api.service.external_data;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.external_data.gender.GenderRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.gender.GenderResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Gender;

public interface IGender {
    Gender getNoneGender();
    Gender getGenderByName(String genderName);
    
    GenderResponseDto showGender(String genderName, Integer idGender);
    List<GenderResponseDto> showAllGender();

    GenderResponseDto createNewGender(GenderRequestDto dto);

    void deleteGender(String genderName, Integer idGender);

    GenderResponseDto updateGenderName(String genderName, Integer idGender, GenderRequestDto dto);

    Gender findGenderByNameOrId(String genderName, Integer idGender);
}
