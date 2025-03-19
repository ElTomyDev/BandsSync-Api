package com.heavydelay.BandsSync.Api.service.musical_resources.impl.setlist;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.musical_resources.setlist.SetlistRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.musical_resources.setlist.SetlistResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.Setlist;
import com.heavydelay.BandsSync.Api.model.mapper.musical_resources.ISetlistMapper;
import com.heavydelay.BandsSync.Api.repository.band.BandRepository;
import com.heavydelay.BandsSync.Api.repository.musical_resources.setlist.SetlistRepository;
import com.heavydelay.BandsSync.Api.service.musical_resources.setlist.ISetlist;

@Service
public class SetlistImplService implements ISetlist{


    private SetlistRepository setlistRepository;
    private BandRepository bandRepository;

    private ISetlistMapper setlistMapper;

    public SetlistImplService(SetlistRepository setlistRepository, BandRepository bandRepository,
            ISetlistMapper setlistMapper) {
        this.setlistRepository = setlistRepository;
        this.bandRepository = bandRepository;
        this.setlistMapper = setlistMapper;
        
    }

    @Override
    public SetlistResponseDto createSetlist(Long idBand, SetlistRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deteleSetlist(Long idSetlist, Long idBand, String setlistName) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<SetlistResponseDto> showAllSetlist(Long bandId, Boolean inUse, boolean detailed) {
        List<Setlist> setlists = (List<Setlist>) this.findAllSetlist(bandId, inUse);

        Function<Setlist, SetlistResponseDto> mapper = detailed ? setlistMapper::toDetailedDto : setlistMapper::toBasicDto;

        return setlists.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public SetlistResponseDto showSetlist(Long idSetlist, Long idBand, String setlistName, boolean detailed) {
        Setlist setlist = this.findSetlist(idSetlist, idBand, setlistName);
        return detailed ? setlistMapper.toDetailedDto(setlist) : setlistMapper.toBasicDto(setlist);
    }

    @Override
    public SetlistResponseDto updateInUse(Long idSetlist, Long idBand, String setlistName, SetlistRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SetlistResponseDto updateSetlistName(Long idSetlist, Long idBand, String setlistName,
            SetlistRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    ////////// AUXILIAR METHODS ////////////////////////////////////////////
    private Iterable<Setlist> findAllSetlist(Long idBand, Boolean inUse){
        Iterable<Setlist> setlists;
        if (idBand != null){
            Band band = bandRepository.findById(idBand).orElseThrow(
                () -> new ResourceNotFoundException("The Band with ID '" + idBand + "' not found")
            );

            setlists = setlistRepository.findAllByBand(band);
        } else if (inUse != null){
            setlists = setlistRepository.findAllByInUse(inUse);
        } else {
            setlists = setlistRepository.findAll();
        }
        return setlists;
    }

    private Setlist findSetlist(Long idSetlist, Long idBand, String setlistName){
        Setlist setlist;
        if(idSetlist != null){
            setlist = setlistRepository.findById(idSetlist).orElseThrow(
                () -> new ResourceNotFoundException("The setlist with ID '" + idSetlist + "' not found")
            );
        }else if (idBand != null && setlistName != null){
            Band band = bandRepository.findById(idBand).orElseThrow(
                () -> new ResourceNotFoundException("The Band with ID '" + idBand + "' not found")
            );

            setlist = setlistRepository.findByBandAndSetlistName(band, setlistName).orElseThrow(
                () -> new ResourceNotFoundException("The setlist with name '" + setlistName + "' not found")
            );
        }else {
            throw new IllegalArgumentException("Paremeters cannot be null");
        }
        return setlist;
    }
}
