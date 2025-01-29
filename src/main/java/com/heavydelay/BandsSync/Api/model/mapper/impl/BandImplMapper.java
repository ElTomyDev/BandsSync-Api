package com.heavydelay.BandsSync.Api.model.mapper.impl;

import com.heavydelay.BandsSync.Api.model.dto.band.BandResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.mapper.IBandMapper;

public class BandImplMapper implements IBandMapper{

    @Override
    public BandResponseDto toAccesCodeDto(Band band) {
        return BandResponseDto.builder().accessCode(band.getAccessCode()).build();
    }

    @Override
    public BandResponseDto toBasicDto(Band band) {
        return BandResponseDto.builder()
               .idBand(band.getIdBand())
               .genderName(band.getGender().getGenderName())
               .socialLinks(band.getSocialLinks())
               .bandName(band.getBandName())
               .isSolist(band.getIsSolist())
               .findMembers(band.getFindMembers())
               .biography(band.getBiography())
               .build();
    }

    @Override
    public BandResponseDto toDetailedDto(Band band) {
        return BandResponseDto.builder()
               .idBand(band.getIdBand())
               .gender(band.getGender())
               .socialLinks(band.getSocialLinks())
               .imageURL(band.getImageURL())
               .bandName(band.getBandName())
               .isSolist(band.getIsSolist())
               .findMembers(band.getFindMembers())
               .biography(band.getBiography())
               .createDate(band.getCreateDate())
               .build();
    }

}
