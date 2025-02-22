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
import com.heavydelay.BandsSync.Api.repository.external_data.RoleRepository;
import com.heavydelay.BandsSync.Api.repository.user.UserRepository;
import com.heavydelay.BandsSync.Api.service.band.IBandMember;

@Service
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
    public BandMemberResponseDto showMember(String username, String bandName, Long idBand, Long idUser, Long idMember, boolean detailed) {
        BandMember member = this.findMemberByBandOrUserOrId(username, bandName, idBand, idUser, idMember);
        
        return detailed ? bandMemberMapper.toDetailedDto(member) : bandMemberMapper.toBasicDto(member);
    }
    
    ///////// DELETE MEMBERS /////////////////////////////////////////////////////////
    @Override
    public void deleteMember(String username, String bandName, Long idBand, Long idUser, Long idMember) {
        BandMember member = this.findMemberByBandOrUserOrId(username, bandName, idBand, idUser, idMember);
        
        bandMemberRepository.delete(member);
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
                               .isAdmin(this.selectAdminIfIsFirstAdmin(band))
                               .role(roleRepository.findByRoleName(dto.getRoleName()).orElseThrow(
                                    () -> new ResourceNotFoundException("The role with name '" + dto.getRoleName() + "' was not found")
                               ))
                               .leaveDate(null)
                               .build();

        bandMemberRepository.save(newMember);

        return bandMemberMapper.toBasicDto(newMember);
    }

    @Override
    public BandMemberResponseDto leaveBand(String username, Long idUser, Long idMember) {
        BandMember member = this.findMemberByBandOrUserOrId(username, null, null, idUser, idMember);
        member.setIsAdmin(false);
        member.setLeaveDate(LocalDateTime.now());
        bandMemberRepository.save(member);
        return bandMemberMapper.toBasicDto(member);
    }
    
    ///////// UPDATE /////////////////////////////////////////////////////////
    @Override
    public BandMemberResponseDto updateGender(String username, String bandName, Long idBand, Long idUser, Long idMember, BandMemberRequestDto dto) {
        BandMember member = this.findMemberByBandOrUserOrId(username, bandName, idBand, idUser, idMember);
        
        member.setRole(roleRepository.findByRoleName(dto.getRoleName()).orElseThrow(
            () -> new ResourceNotFoundException("The role with name '" + dto.getRoleName() + "' was not found")
        ));
        
        bandMemberRepository.save(member);
        return bandMemberMapper.toBasicDto(member);
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

    private boolean selectAdminIfIsFirstAdmin(Band band){
        if(bandMemberRepository.findAllByBand(band).isEmpty()){
            return true;
        }
        return false;
    }
}
