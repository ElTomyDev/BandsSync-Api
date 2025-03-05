package com.heavydelay.BandsSync.Api.service.band.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.band.BandRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.band.BandResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.BandMember;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;
import com.heavydelay.BandsSync.Api.model.mapper.band.IBandMapper;
import com.heavydelay.BandsSync.Api.model.mapper.external_data.ISocialLinksMapper;
import com.heavydelay.BandsSync.Api.repository.band.BandMemberRepository;
import com.heavydelay.BandsSync.Api.repository.band.BandRepository;
import com.heavydelay.BandsSync.Api.repository.external_data.GenderRepository;
import com.heavydelay.BandsSync.Api.repository.external_data.RoleRepository;
import com.heavydelay.BandsSync.Api.repository.external_data.SocialLinksRepository;
import com.heavydelay.BandsSync.Api.repository.user.UserRepository;
import com.heavydelay.BandsSync.Api.service.band.IBand;
import com.heavydelay.BandsSync.Api.service.external_data.IGender;
import com.heavydelay.BandsSync.Api.service.external_data.IRole;
import com.heavydelay.BandsSync.Api.service.external_data.ISocialLinks;
import com.heavydelay.BandsSync.Api.util.AccessCodeGenerator;

@Service
public class BandImplService implements IBand{
    
    // Repositorios
    private BandRepository bandRepository;
    private UserRepository userRepository;

    private GenderRepository genderRepository; // seguro a borrar
    private SocialLinksRepository socialRepository;
    private BandMemberRepository memberRepository;
    private RoleRepository roleRepository;

    // Servicios
    private IGender genderService;
    private ISocialLinks socialService;
    private IRole roleService;

    // Mapeos
    private IBandMapper bandMapper;

    private ISocialLinksMapper socialMapper;// seguro a borrar

    

    public BandImplService(BandRepository bandRepository, GenderRepository genderRepository,
            SocialLinksRepository socialRepository, BandMemberRepository memberRepository,
            UserRepository userRepository, RoleRepository roleRepository, IBandMapper bandMapper,
            ISocialLinksMapper socialMapper) {
        this.bandRepository = bandRepository;
        this.genderRepository = genderRepository;
        this.socialRepository = socialRepository;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.bandMapper = bandMapper;
        this.socialMapper = socialMapper;
    }

    /////////// SHOW BAND ///////////////////////////////////////////////////////////
    @Override
    public List<BandResponseDto> showAllBands(boolean detailed) {
        List<Band> bands = (List<Band>) bandRepository.findAll();

        // Selección del DTO a usar 
        Function<Band, BandResponseDto> mapper = detailed ? bandMapper::toDetailedDto : bandMapper::toBasicDto;

        // Retorno y mapea la lista con todas las bandas
        return bands.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public BandResponseDto showBand(String bandName, Long id, boolean detailed) {

        Band band = this.findBandByIdOrBandname(bandName, id);

        return detailed ? bandMapper.toDetailedDto(band) : bandMapper.toBasicDto(band);
    }

    /////////// CREATE BAND ///////////////////////////////////////////////////////////
    @Override
    public BandResponseDto createBand(BandRequestDto dto) {

        if(bandRepository.findByBandName(dto.getBandName()).orElse(null) != null){
            throw new IllegalArgumentException("The band name '" + dto.getBandName() + "' is already in use");
        }

        Band newBand = Band.builder()
                       .bandName(dto.getBandName())
                       .gender(genderService.getNoneGender())
                       .socialLinks(socialService.createEmptySocialLinks())
                       .accessCode(AccessCodeGenerator.generateAccessCode(6))
                       .build();

        bandRepository.save(newBand);

        BandMember newMember = BandMember.builder()
                               .band(newBand)
                               .isAdmin(true)
                               .user(userRepository.findByUsername(dto.getUsername()).orElseThrow(
                                    () -> new ResourceNotFoundException("The user with username '"+ dto.getUsername() + "' not found")
                                ))
                               .role(roleRepository.findByRoleName(dto.getRoleName()).orElseThrow(
                                    () -> new ResourceNotFoundException("The role with name '"+ dto.getRoleName() + "' not found")
                                ))
                               .build();
        
        memberRepository.save(newMember);

        return bandMapper.toBasicDto(newBand);
    }

    /////////// DELETE BAND ///////////////////////////////////////////////////////////
    @Override
    public void deleteBand(String bandName, Long id) {
        Band bandDelete = this.findBandByIdOrBandname(bandName, id);

        bandRepository.delete(bandDelete);
        socialService.deleteSocialLinksByBand(bandDelete);
    }

    
    /////////// UPDATE METHODS ///////////////////////////////////////////////////////////
    @Override
    public BandResponseDto updateAccessCode(String bandName, Long id) {
        Band bandUpdate = this.findBandByIdOrBandname(bandName, id);

        String generatedCode = AccessCodeGenerator.generateAccessCode(6);
        
        bandUpdate.setAccessCode(generatedCode);
        bandRepository.save(bandUpdate);

        return bandMapper.toBasicDto(bandUpdate);
    }

    @Override
    public BandResponseDto updateBandName(String bandName, Long id, BandRequestDto dto) {
        Band bandUpdate = this.findBandByIdOrBandname(bandName, id);
        
        bandUpdate.setBandName(dto.getBandName());
        bandRepository.save(bandUpdate);

        return bandMapper.toBasicDto(bandUpdate);
    }

    @Override
    public BandResponseDto updateBiography(String bandName, Long id, BandRequestDto dto) {
        Band bandUpdate = this.findBandByIdOrBandname(bandName, id);
        
        bandUpdate.setBiography(dto.getBiography());
        bandRepository.save(bandUpdate);

        return bandMapper.toBasicDto(bandUpdate);
    }

    @Override
    public BandResponseDto updateFindMembers(String bandName, Long id, BandRequestDto dto) {
        Band bandUpdate = this.findBandByIdOrBandname(bandName, id);
        
        bandUpdate.setFindMembers(dto.getFindMembers());
        bandRepository.save(bandUpdate);

        return bandMapper.toBasicDto(bandUpdate);
    }

    @Override
    public BandResponseDto updateGender(String bandName, Long id, BandRequestDto dto) {
        Band bandUpdate = this.findBandByIdOrBandname(bandName, id);
        
        bandUpdate.setGender(genderService.getGenderByName(dto.getGenderName()));
        bandRepository.save(bandUpdate);

        return bandMapper.toBasicDto(bandUpdate);
    }

    @Override
    public BandResponseDto updateImageUrl(String bandName, Long id, BandRequestDto dto) {
        Band bandUpdate = this.findBandByIdOrBandname(bandName, id);
        
        bandUpdate.setImageURL(dto.getImageURL());
        bandRepository.save(bandUpdate);

        return bandMapper.toBasicDto(bandUpdate);
    }

    @Override
    public BandResponseDto updateIsSolist(String bandName, Long id, BandRequestDto dto) {
        Band bandUpdate = this.findBandByIdOrBandname(bandName, id);
        
        bandUpdate.setIsSolist(dto.getIsSolist());
        bandRepository.save(bandUpdate);

        return bandMapper.toBasicDto(bandUpdate);
    }

    @Override
    public SocialLinksResponseDto updateSocialLinks(String bandName, Long id, SocialLinksRequestDto dto) {
        Band bandUpdate = this.findBandByIdOrBandname(bandName, id);

        SocialLinks social = socialRepository.findById(bandUpdate.getSocialLinks().getIdSocial()).orElseThrow(
            () -> new ResourceNotFoundException("The Social links with ID '" + bandUpdate.getSocialLinks().getIdSocial() + "' was not found")
        );

        social.setInstagram(dto.getInstagram());
        social.setFacebook(dto.getFacebook());
        social.setTwitter(dto.getTwitter());
        social.setTiktok(dto.getTiktok());
        social.setReddit(dto.getReddit());
        social.setYoutube(dto.getYoutube());
        social.setSpotify(dto.getSpotify());
        social.setBandcamp(dto.getBandcamp());
        social.setSoundcloud(dto.getSoundcloud());

        socialRepository.save(social);

        return socialMapper.toBasicDto(social);
    }

    /////////// AUXILIAR ///////////////////////////////////////////////////////////
    @Override
    public Band findBandByIdOrBandname(String bandName, Long id) {
        Band band;

        if(bandName != null){ // si el band name no es null
            band = bandRepository.findByBandName(bandName).orElseThrow(
                () -> new ResourceNotFoundException("The band with band name '" + bandName + "' was not found")
            );
        }else if(id != null){ // si el id no es null
            band = bandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The band with ID '" + id + "' was not found")
            );
        }else{ // si el band name y el id son null
            throw new IllegalArgumentException("Both band name and id cannot be null");
        }

        return band;
    }

}
