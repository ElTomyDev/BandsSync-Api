package com.heavydelay.BandsSync.Api.service.external_data.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.external_data.gender.GenderRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.gender.GenderResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Gender;
import com.heavydelay.BandsSync.Api.model.entity.Role;
import com.heavydelay.BandsSync.Api.model.mapper.external_data.IGenderMapper;
import com.heavydelay.BandsSync.Api.repository.external_data.GenderRepository;
import com.heavydelay.BandsSync.Api.service.external_data.IGender;

@Service
public class GenderImplService implements IGender{
    
    private GenderRepository genderRepository;
    private IGenderMapper genderMapper;

    public GenderImplService(GenderRepository genderRepository, IGenderMapper genderMapper) {
        this.genderRepository = genderRepository;
        this.genderMapper = genderMapper;
    }

    @Override
    public GenderResponseDto createNewGender(GenderRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteGender(String genderName, Integer idGender) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Gender getGenderByName(String genderName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Gender getNoneGender() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GenderResponseDto> showAllGender() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GenderResponseDto showGender(String genderName, Integer idGender) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GenderResponseDto updateGenderName(String genderName, Integer idGender, GenderRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Gender findGenderByNameOrId(String genderName, Integer idGender) {
        Gender gender;

        if(genderName != null){
            gender = genderRepository.findByGenderName(genderName).orElseThrow(
                () -> new ResourceNotFoundException("The Gender with name '" + genderName + "' was not found")
            );
        } else if (idGender != null){
            gender = genderRepository.findById(idGender).orElseThrow(
                () -> new ResourceNotFoundException("The Gender with ID '" + idGender + "' was not found")
            );
        } else{
            throw new IllegalArgumentException("Both genderName and idGender cannot be null");
        }

        return gender;
    }
    
}
