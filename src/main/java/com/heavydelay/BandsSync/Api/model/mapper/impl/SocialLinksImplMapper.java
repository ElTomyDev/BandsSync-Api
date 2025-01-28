package com.heavydelay.BandsSync.Api.model.mapper.impl;

import com.heavydelay.BandsSync.Api.model.dto.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;
import com.heavydelay.BandsSync.Api.model.mapper.ISocialLinksMapper;

public class SocialLinksImplMapper implements ISocialLinksMapper{

    @Override
    public SocialLinksResponseDto toBasicDto(SocialLinks social) {
        return SocialLinksResponseDto.builder()
               .idSocial(social.getIdSocial())
               .instagram(social.getInstagram())
               .facebook(social.getFacebook())
               .twitter(social.getTwitter())
               .tiktok(social.getTiktok())
               .reddit(social.getReddit())
               .youtube(social.getYoutube())
               .spotify(social.getSpotify())
               .soundcloud(social.getSoundcloud())
               .bandcamp(social.getBandcamp())
               .build();
    }

}
