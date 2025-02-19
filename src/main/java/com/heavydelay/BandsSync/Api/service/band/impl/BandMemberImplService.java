package com.heavydelay.BandsSync.Api.service.band.impl;

import java.util.List;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.BandMember;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.mapper.band.IBandMemberMapper;
import com.heavydelay.BandsSync.Api.repository.band.BandMemberRepository;
import com.heavydelay.BandsSync.Api.repository.band.BandRepository;
import com.heavydelay.BandsSync.Api.repository.external_data.RoleRepository;
import com.heavydelay.BandsSync.Api.repository.user.UserRepository;
import com.heavydelay.BandsSync.Api.service.band.IBandMember;

public class BandMemberImplService implements IBandMember{

    // Repositorios
    private BandMemberRepository bandMemberRepository;
    private BandRepository bandRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    // Mapeos
    private IBandMemberMapper bandMemberMapper;

    public BandMemberImplService(BandMemberRepository bandMemberRepository, BandRepository bandRepository,
            UserRepository userRepository, RoleRepository roleRepository, IBandMemberMapper bandMemberMapper) {
        this.bandMemberRepository = bandMemberRepository;
        this.bandRepository = bandRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        this.bandMemberMapper = bandMemberMapper;
    }

    ///////// SHOW MEMBERS /////////////////////////////////////////////////////////
    @Override
    public List<BandMemberResponseDto> showAllMembers(String username, String bandName, Long id, boolean detailed) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public BandMemberResponseDto showMember(String username, String bandName, Long id, boolean detailed) {
        // TODO Auto-generated method stub
        return null;
    }
    
    ///////// DELETE MEMBERS /////////////////////////////////////////////////////////
    @Override
    public BandMemberResponseDto deleteMember(String username, String bandName, Long idBand, Long idMember) {
        // TODO Auto-generated method stub
        return null;
    }

    ///////// AUTH AND REGISTER /////////////////////////////////////////////////////////
    @Override
    public BandMemberResponseDto joinBand(BandMemberRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BandMemberResponseDto leaveBand(String username, String bandName, Long idBand, Long idMember) {
        // TODO Auto-generated method stub
        return null;
    }

    ///////// UPDATE /////////////////////////////////////////////////////////
    @Override
    public BandMemberResponseDto updateGender(String username, String bandName, Long id, BandMemberRequestDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    ///////// AUXILIARES /////////////////////////////////////////////////////
    @Override
    public BandMember findMemberByBandOrUserOrId(String username, String bandName, Long idBand, Long idUser, Long idMember) {
        BandMember bandMember;

        if (bandName != null){ // si el band name no es null
            Band band = bandRepository.findByBandName(bandName).orElseThrow(
                () -> new ResourceNotFoundException("The band with band name '" + bandName + "' was not found")
            );

            bandMember = bandMemberRepository.findByBand(band).orElseThrow(
                () -> new ResourceNotFoundException("Member is not a member of the band with name '" + bandName + "' ")
            );
        }else if (idBand != null){ // si el idBand no es null
            Band band = bandRepository.findById(idBand).orElseThrow(
                () -> new ResourceNotFoundException("The band with ID '" + idBand + "' was not found")
            );
            
            bandMember = bandMemberRepository.findByBand(band).orElseThrow(
                () -> new ResourceNotFoundException("Member is not a member of the band with ID '" + idBand + "' ")
            );
        }else if (username != null){ // si el username no es null
            User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("The user with username '" + username + "' was not found")
            );
            
            bandMember = bandMemberRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("User '"+ username +"' is not a member of any band")
            );
        
        }else if (idUser != null){ // si el idUser no el null
            User user = userRepository.findById(idUser).orElseThrow(
                () -> new ResourceNotFoundException("The user with ID '" + idUser + "' was not found")
            );
            
            bandMember = bandMemberRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("User with ID '"+ idUser +"' is not a member of any band")
            );
        }else if (idMember != null){ // si idMember no es null
            bandMember = bandMemberRepository.findById(idMember).orElseThrow(
                () -> new ResourceNotFoundException("The member with ID '" + idMember + "' was not found")
            );
        }else{ // si todos los parametros son null
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        return bandMember;
    }
}
