package com.heavydelay.BandsSync.Api.model.mapper.external_data.impl;

import org.springframework.stereotype.Component;

import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;
import com.heavydelay.BandsSync.Api.model.mapper.external_data.ISocialLinksMapper;

@Component
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
