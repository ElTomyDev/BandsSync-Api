package com.heavydelay.BandsSync.Api.service.band.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.band.BandRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.band.BandResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.mapper.band.IBandMapper;
import com.heavydelay.BandsSync.Api.repository.band.BandRepository;
import com.heavydelay.BandsSync.Api.service.band.IBand;

@Service
public class BandImplService implements IBand{
    
    private BandRepository bandRepository;
    private IBandMapper bandMapper;

    public BandImplService(BandRepository bandRepository, IBandMapper bandMapper) {
        this.bandRepository = bandRepository;
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
        // TODO Auto-generated method stub
        return null;
    }

    /////////// DELETE BAND ///////////////////////////////////////////////////////////
    @Override
    public void deleteBand(String bandName, Long id) {
        // TODO Auto-generated method stub
        
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
