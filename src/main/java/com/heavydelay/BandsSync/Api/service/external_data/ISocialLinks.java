package com.heavydelay.BandsSync.Api.service.external_data;

import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;
import com.heavydelay.BandsSync.Api.model.entity.User;

public interface ISocialLinks {
    void deleteSocialLinksByUser(User user);
    void deleteSocialLinksByBand(Band band);
    SocialLinks createEmptySocialLinks();
    SocialLinksResponseDto updateSocialLinksForUser(User user, SocialLinksRequestDto dto);
}
