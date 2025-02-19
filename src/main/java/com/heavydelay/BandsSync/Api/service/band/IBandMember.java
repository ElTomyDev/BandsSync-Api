package com.heavydelay.BandsSync.Api.service.band;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.BandMember;

public interface IBandMember {
    // VER MIEMBROS
    List<BandMemberResponseDto> showAllMembers(String username, String bandName, Long id, boolean detailed);
    BandMemberResponseDto showMember(String username, String bandName, Long id, boolean detailed);

    // AUTH AND REGISTER
    BandMemberResponseDto joinBand(BandMemberRequestDto dto);
    BandMemberResponseDto leaveBand(String username, String bandName, Long idBand, Long idMember);

    // ELIMINAR BANDA
    BandMemberResponseDto deleteMember(String username, String bandName, Long idBand, Long idMember);
    

    // METODOS UPDATE
    BandMemberResponseDto updateGender(String username, String bandName, Long id, BandMemberRequestDto dto);
    

    // AUXILIARES
    BandMember findMemberByBandOrUserOrId(String username, String bandName, Long idBand, Long idUser, Long idMember);
}
