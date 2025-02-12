package com.heavydelay.BandsSync.Api.model.mapper.external_data;

import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;

public interface ISocialLinksMapper {
    SocialLinksResponseDto toBasicDto(SocialLinks social);
}
