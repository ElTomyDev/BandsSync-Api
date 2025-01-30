package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.band_member.BandMemberResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.BandMember;

public interface IBandMemberMapper {
    BandMemberResponseDto toBasicDto(BandMember bandMember);
    BandMemberResponseDto toDetailedDto(BandMember bandMember);

}
