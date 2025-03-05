package com.heavydelay.BandsSync.Api.service.external_data.impl;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.mapper.external_data.ISocialLinksMapper;
import com.heavydelay.BandsSync.Api.repository.external_data.SocialLinksRepository;
import com.heavydelay.BandsSync.Api.service.external_data.ISocialLinks;

@Service
public class SocialLinksImplService implements ISocialLinks{

    SocialLinksRepository socialRepository;
    ISocialLinksMapper socialMapper;

    public SocialLinksImplService(SocialLinksRepository socialRepository, ISocialLinksMapper socialMapper) {
        this.socialRepository = socialRepository;
        this.socialMapper = socialMapper;
    }
    @Override
    public SocialLinks createEmptySocialLinks() {
        SocialLinks social = SocialLinks.builder()
                             .instagram("")
                             .facebook("")
                             .twitter("")
                             .tiktok("")
                             .reddit("")
                             .youtube("")
                             .spotify("")
                             .soundcloud("")
                             .bandcamp("")
                             .build();
        socialRepository.save(social);
        return social;
    }
    
    @Override
    public void deleteSocialLinksByUser(User user) {
        SocialLinks socialDelete = socialRepository.findById(user.getSocialLinks().getIdSocial()).orElseThrow(
            () -> new ResourceNotFoundException("The Social links not found")
        );
        socialRepository.delete(socialDelete);
    }

    @Override
    public void deleteSocialLinksByBand(Band band) {
        SocialLinks socialDelete = socialRepository.findById(band.getSocialLinks().getIdSocial()).orElseThrow(
            () -> new ResourceNotFoundException("The Social links not found")
        );
        socialRepository.delete(socialDelete);
    }

    @Override
    public SocialLinksResponseDto updateSocialLinksForUser(User user, SocialLinksRequestDto dto) {
        SocialLinks socialUpdate = socialRepository.findById(user.getSocialLinks().getIdSocial()).orElseThrow(
            () -> new ResourceNotFoundException("The Social links not found")
        );

        if (!dto.getInstagram().isEmpty()) {
            socialUpdate.setInstagram(dto.getInstagram());
        }
        if (!dto.getFacebook().isEmpty()) {
            socialUpdate.setFacebook(dto.getFacebook());
        }
        if (!dto.getTwitter().isEmpty()) {
            socialUpdate.setTwitter(dto.getTwitter());
        }
        if (!dto.getTiktok().isEmpty()) {
            socialUpdate.setTiktok(dto.getTiktok());
        }
        if (!dto.getReddit().isEmpty()) {
            socialUpdate.setReddit(dto.getReddit());
        }
        if (!dto.getYoutube().isEmpty()) {
            socialUpdate.setYoutube(dto.getYoutube());
        }
        if (!dto.getSpotify().isEmpty()) {
            socialUpdate.setSpotify(dto.getSpotify());
        }
        if (!dto.getSoundcloud().isEmpty()) {
            socialUpdate.setSoundcloud(dto.getSoundcloud());
        }
        if (!dto.getBandcamp().isEmpty()) {
            socialUpdate.setBandcamp(dto.getBandcamp());
        }

        socialRepository.save(socialUpdate);
        return socialMapper.toBasicDto(socialUpdate);
    }

}
