package com.heavydelay.BandsSync.Api.model.mapper.impl;

import com.heavydelay.BandsSync.Api.model.dto.band_member.BandMemberResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.BandMember;
import com.heavydelay.BandsSync.Api.model.mapper.IBandMemberMapper;

public class BandMemberImplMapper implements IBandMemberMapper{

    @Override
    public BandMemberResponseDto toBasicDto(BandMember bandMember) {
        return BandMemberResponseDto.builder()
               .idBandMember(bandMember.getIdBandMember())
               .username(bandMember.getUser().getUsername())
               .bandName(bandMember.getBand().getBandName())
               .roleName(bandMember.getRole().getRoleName())
               .isAdmin(bandMember.getIsAdmin())
               .build();
    }

    @Override
    public BandMemberResponseDto toDetailedDto(BandMember bandMember) {
        return BandMemberResponseDto.builder()
               .idBandMember(bandMember.getIdBandMember())
               .user(bandMember.getUser())
               .band(bandMember.getBand())
               .role(bandMember.getRole())
               .isAdmin(bandMember.getIsAdmin())
               .joinDate(bandMember.getJoinDate())
               .leaveDate(bandMember.getLeaveDate())
               .build();
    }

}
