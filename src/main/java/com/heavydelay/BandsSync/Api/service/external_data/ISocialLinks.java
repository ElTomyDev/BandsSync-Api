package com.heavydelay.BandsSync.Api.service.external_data;

import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksRequestDto;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;
import com.heavydelay.BandsSync.Api.model.entity.User;

public interface ISocialLinks {
    void deleteSocialLinksByUser(User user);
    SocialLinks createSocialLinksForUser(User user);
    SocialLinks updateSocialLinksForUser(User user, SocialLinksRequestDto dto);
}
