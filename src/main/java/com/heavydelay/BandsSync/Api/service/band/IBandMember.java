package com.heavydelay.BandsSync.Api.service.band;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.BandMember;
import com.heavydelay.BandsSync.Api.model.entity.User;

public interface IBandMember {
    // VER MIEMBROS
    List<BandMemberResponseDto> showAllMembers(boolean detailed);
    List<BandMemberResponseDto> showAllMembersByBand(String bandName, Long idBand, boolean detailed);
    BandMemberResponseDto showMember(String username, Long idUser, String bandName, Long idBand, Long idMember, boolean detailed);

    // AUTH AND REGISTER
    BandMemberResponseDto joinBand(String username, Long idUser, String bandName, Long idBand, BandMemberRequestDto dto);
    BandMemberResponseDto leaveBand(String username, Long idUser, String bandName, Long idBand);
    void createFirstMember(User user, Band band);

    // ELIMINAR BANDA
    void deleteMember(String username, Long idUser, String bandName, Long idBand, Long idMember);
    void deleteAllMembersByBand(Band band);

    // METODOS UPDATE
    BandMemberResponseDto updateRole(String username, Long idUser, String bandName, Long idBand, Long idMember, BandMemberRequestDto dto);
    BandMemberResponseDto updateAdmin(String username, Long idUser, String bandName, Long idBand, Long idMember, Boolean isAdmin);

    

    // AUXILIARES
    BandMember findMemberByBandAndUserOrId(String username, Long idUser, String bandName, Long idBand, Long idMember);
}
