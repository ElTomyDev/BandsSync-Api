package com.heavydelay.BandsSync.Api.service.band.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.BandMember;
import com.heavydelay.BandsSync.Api.model.entity.User;
import com.heavydelay.BandsSync.Api.model.mapper.band.IBandMemberMapper;
import com.heavydelay.BandsSync.Api.repository.band.BandMemberRepository;
import com.heavydelay.BandsSync.Api.repository.band.BandRepository;
import com.heavydelay.BandsSync.Api.repository.user.UserRepository;
import com.heavydelay.BandsSync.Api.service.band.IBandMember;
import com.heavydelay.BandsSync.Api.service.external_data.IRole;

@Service
public class BandMemberImplService implements IBandMember{

    // Repositorios
    private BandMemberRepository bandMemberRepository;
    private BandRepository bandRepository;
    private UserRepository userRepository;

    // Servicios
    private IRole roleService;

    // Mapeos
    private IBandMemberMapper bandMemberMapper;

    public BandMemberImplService(BandMemberRepository bandMemberRepository, BandRepository bandRepository,
            UserRepository userRepository, IBandMemberMapper bandMemberMapper) {
        this.bandMemberRepository = bandMemberRepository;
        this.bandRepository = bandRepository;
        this.userRepository = userRepository;

        this.bandMemberMapper = bandMemberMapper;
    }

    ///////// SHOW MEMBERS /////////////////////////////////////////////////////////
    @Override
    public List<BandMemberResponseDto> showAllMembers(boolean detailed) {
        List<BandMember> members = (List<BandMember>) bandMemberRepository.findAll();
        
        // Selección del DTO a usar 
        Function<BandMember, BandMemberResponseDto> mapper = detailed ? bandMemberMapper::toDetailedDto : bandMemberMapper::toBasicDto;
        
        // Retorno y mapea la lista con todas las bandas
        return members.stream().map(mapper).collect(Collectors.toList());
    }
    
    @Override
    public List<BandMemberResponseDto> showAllMembersByBand(String bandName, Long idBand, boolean detailed){
        List<BandMember> members;
        
        if (bandName != null){
            Band band = bandRepository.findByBandName(bandName).orElseThrow(
                () -> new ResourceNotFoundException("The band with band name '" + bandName + "' was not found")
            );
            members = (List<BandMember>) bandMemberRepository.findAllByBand(band);
        }else if (idBand != null){
            Band band = bandRepository.findById(idBand).orElseThrow(
                () -> new ResourceNotFoundException("The band with ID '" + idBand + "' was not found")
            );
            members = (List<BandMember>) bandMemberRepository.findAllByBand(band);
        }else{ // si todos los parametros son null
            throw new IllegalArgumentException("Parameters cannot be null");
        }
            
        Function<BandMember, BandMemberResponseDto> mapper = detailed ? bandMemberMapper::toDetailedDto : bandMemberMapper::toBasicDto;
        return members.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public BandMemberResponseDto showMember(String username, Long idUser, String bandName, Long idBand, Long idMember, boolean detailed) {
        BandMember member = this.findMemberByBandAndUserOrId(username, idUser, bandName, idBand, idMember);
        
        return detailed ? bandMemberMapper.toDetailedDto(member) : bandMemberMapper.toBasicDto(member);
    }
    
    ///////// DELETE MEMBERS /////////////////////////////////////////////////////////
    @Override
    public void deleteMember(String username, Long idUser, String bandName, Long idBand, Long idMember) {
        BandMember member = this.findMemberByBandAndUserOrId(username, idUser, bandName, idBand, idMember);
        
        bandMemberRepository.delete(member);
    }

    @Override
    public void deleteAllMembersByBand(Band band){
        if (band == null){
            throw new IllegalArgumentException("Band cannot be null");
        }

        List<BandMember> members = (List<BandMember>) bandMemberRepository.findAllByBand(band);
        bandMemberRepository.deleteAll(members);
    }

    ///////// AUTH AND REGISTER /////////////////////////////////////////////////////////
    @Override
    public BandMemberResponseDto joinBand(String username, Long idUser, BandMemberRequestDto dto) {
        User user;
        Band band = bandRepository.findById(dto.getIdBand()).orElseThrow(
            () -> new ResourceNotFoundException("The band with band name '" + dto.getIdBand() + "' was not found")
        );

        if (!dto.getAccessCode().equals(band.getAccessCode())){
            throw new IllegalArgumentException("The access code is incorrect");
        }

        if(username != null){
            user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("The user with username '" + username + "' was not found")
            );
        }else if (idUser != null){
            user = userRepository.findById(idUser).orElseThrow(
                () -> new ResourceNotFoundException("The user with ID '" + idUser + "' was not found")
            );
        }else{ // si todos los parametros son null
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        BandMember newMember = BandMember.builder()
                               .band(band)
                               .user(user)
                               .isAdmin(false)
                               .role(roleService.getRoleByName(dto.getRoleName()))
                               .leaveDate(null)
                               .build();

        bandMemberRepository.save(newMember);

        return bandMemberMapper.toBasicDto(newMember);
    }

    @Override
    public BandMemberResponseDto leaveBand(String username, Long idUser, String bandName, Long idBand, Long idMember, BandMemberRequestDto dto) {
        BandMember member = this.findMemberByBandAndUserOrId(username, idUser, bandName, idBand, idMember);
        
        
        member.setIsAdmin(false);
        member.setLeaveDate(LocalDateTime.now());
        bandMemberRepository.save(member);
        return bandMemberMapper.toBasicDto(member);
    }
    
    @Override
    public void createFirstMember(User user, Band band){
        BandMember firstMember = BandMember.builder()
                                 .band(band)
                                 .user(user)
                                 .role(roleService.getNoneRole())
                                 .isAdmin(true)
                                 .build();
        bandMemberRepository.save(firstMember);
    }

    ///////// UPDATE /////////////////////////////////////////////////////////
    @Override
    public BandMemberResponseDto updateRole(String username, Long idUser, String bandName, Long idBand, Long idMember, BandMemberRequestDto dto) {
        BandMember member = this.findMemberByBandAndUserOrId(username, idUser, bandName, idBand, idMember);
        
        member.setRole(roleService.getRoleByName(dto.getRoleName()));
            
        bandMemberRepository.save(member);
        return bandMemberMapper.toBasicDto(member);
    }
        
    @Override
    public BandMemberResponseDto updateAdmin(String username, Long idUser, String bandName, Long idBand, Long idMember, Boolean isAdmin){
        if(isAdmin == null){
            throw new IllegalArgumentException("isAdmin cannot be null");
        }
        
        BandMember member = this.findMemberByBandAndUserOrId(username, idUser, bandName, idBand, idMember);
        
        member.setIsAdmin(isAdmin);
        bandMemberRepository.save(member);

        return bandMemberMapper.toBasicDto(member);
    }

    ///////// AUXILIARES /////////////////////////////////////////////////////
    @Override 
    public BandMember findMemberByBandAndUserOrId(String username, Long idUser, String bandName, Long idBand, Long idMember){
        if (idMember != null){
            BandMember member = bandMemberRepository.findById(idMember).orElseThrow(
                () -> new ResourceNotFoundException("The member with ID '" + idMember + "' was not found")
            ); 
            return member;
        }
        
        User user;
        Band band;

        if (bandName != null){
            band = bandRepository.findByBandName(bandName).orElseThrow(
                () -> new ResourceNotFoundException("The band with band name '" + bandName + "' was not found")
            );
        } else if (idBand != null){
            band = bandRepository.findById(idBand).orElseThrow(
                () -> new ResourceNotFoundException("The band with band ID '" + idBand + "' was not found")
            );
        }else{
            throw new IllegalArgumentException("bandName or idBand cannot be null");
        }

        if (username != null){
            user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("The user with username '" + username + "' was not found")
            );
        }else if (idUser != null){
            user = userRepository.findById(idUser).orElseThrow(
                () -> new ResourceNotFoundException("The user with ID '" + idUser + "' was not found")
            );
        }else{
            throw new IllegalArgumentException("username or idUser cannot be null");
        }
            
        BandMember member = bandMemberRepository.findByUserAndBand(user, band).orElseThrow(
            () -> new ResourceNotFoundException("member was not found")
        );

        return member;
    }

}
