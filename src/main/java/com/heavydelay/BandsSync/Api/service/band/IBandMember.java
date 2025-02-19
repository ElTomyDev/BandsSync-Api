package com.heavydelay.BandsSync.Api.service.band;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.BandMember;

public interface IBandMember {
    // VER MIEMBROS
    List<BandMemberResponseDto> showAllMembers(boolean detailed);
    List<BandMemberResponseDto> showAllMembersByBand(String bandName, Long idBand, boolean detailed);
    BandMemberResponseDto showMember(String username, String bandName, Long idBand, Long idUser, Long idMember, boolean detailed);

    // AUTH AND REGISTER
    BandMemberResponseDto joinBand(String username, Long idUser, BandMemberRequestDto dto);
    BandMemberResponseDto leaveBand(String username, Long idUser, Long idMember);

    // ELIMINAR BANDA
    void deleteMember(String username, String bandName, Long idBand, Long idUser, Long idMember);
    

    // METODOS UPDATE
    BandMemberResponseDto updateGender(String username, String bandName, Long idBand, Long idUser, Long idMember, BandMemberRequestDto dto);
    

    // AUXILIARES
    BandMember findMemberByBandOrUserOrId(String username, String bandName, Long idBand, Long idUser, Long idMember);
}
