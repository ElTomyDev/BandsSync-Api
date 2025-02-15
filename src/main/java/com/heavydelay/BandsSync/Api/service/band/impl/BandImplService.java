package com.heavydelay.BandsSync.Api.service.band.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.band.BandRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.band.BandResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.SocialLinks;
import com.heavydelay.BandsSync.Api.model.mapper.band.IBandMapper;
import com.heavydelay.BandsSync.Api.repository.band.BandRepository;
import com.heavydelay.BandsSync.Api.repository.external_data.GenderRepository;
import com.heavydelay.BandsSync.Api.repository.external_data.SocialLinksRepository;
import com.heavydelay.BandsSync.Api.service.band.IBand;
import com.heavydelay.BandsSync.Api.util.AccessCodeGenerator;

@Service
public class BandImplService implements IBand{
    
    // Repositorios
    private BandRepository bandRepository;
    private GenderRepository genderRepository;
    private SocialLinksRepository socialRepository;

    // Mapeos
    private IBandMapper bandMapper;


    public BandImplService(BandRepository bandRepository, GenderRepository genderRepository, 
                            SocialLinksRepository socialRepository, IBandMapper bandMapper) {
        this.bandRepository = bandRepository;
        this.genderRepository = genderRepository;
        this.socialRepository = socialRepository;

        this.bandMapper = bandMapper;
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
                       .gender(genderRepository.findByGenderName(dto.getGenderName()).orElseThrow(
                            () -> new ResourceNotFoundException("The gender with gender name '" + dto.getGenderName() + "' not found")
                       ))
                       .accessCode(AccessCodeGenerator.generateAccessCode(6))
                       .build();
        
        // Crear las redes sociales
        SocialLinks socialLinks = new SocialLinks();
        socialRepository.save(socialLinks);

        newBand.setSocialLinks(socialLinks);

        bandRepository.save(newBand);

        return bandMapper.toBasicDto(newBand);
    }

    /////////// DELETE BAND ///////////////////////////////////////////////////////////
    @Override
    public void deleteBand(String bandName, Long id) {
        Band bandDelete = this.findBandByIdOrBandname(bandName, id);

        bandRepository.delete(bandDelete);
    }

    
    /////////// UPDATE METHODS ///////////////////////////////////////////////////////////
    @Override
    public BandResponseDto updateAccessCode(String bandName, Long id, BandRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BandResponseDto updateBandName(String bandName, Long id, BandRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BandResponseDto updateBiography(String bandName, Long id, BandRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BandResponseDto updateFindMembers(String bandName, Long id, BandRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BandResponseDto updateGender(String bandName, Long id, BandRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BandResponseDto updateImageUrl(String bandName, Long id, BandRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BandResponseDto updateIsSolist(String bandName, Long id, BandRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BandResponseDto updateSocialLinks(String bandName, Long id, BandRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
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
