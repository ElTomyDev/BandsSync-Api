package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;

public interface ISocialLinksMapper {
    SocialLinksResponseDto toBasicDto(SocialLinks social);
}
