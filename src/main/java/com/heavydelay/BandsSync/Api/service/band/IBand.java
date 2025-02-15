package com.heavydelay.BandsSync.Api.service.band;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.band.BandRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.band.BandResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Band;

public interface IBand {

    // VER BANDAS
    List<BandResponseDto> showAllBands(boolean detailed);
    BandResponseDto showBand(String bandName, Long id, boolean detailed);

    // CREAR UNA BANDA
    BandResponseDto createBand(BandRequestDto dto);

    // ELIMINAR BANDA
    void deleteBand(String bandName, Long id);

    // METODOS UPDATE
    BandResponseDto updateGender(String bandName, Long id, BandRequestDto dto);
    SocialLinksResponseDto updateSocialLinks(String bandName, Long id, SocialLinksRequestDto dto);
    BandResponseDto updateImageUrl(String bandName, Long id, BandRequestDto dto);
    BandResponseDto updateBandName(String bandName, Long id, BandRequestDto dto);
    BandResponseDto updateAccessCode(String bandName, Long id);
    BandResponseDto updateIsSolist(String bandName, Long id, BandRequestDto dto);
    BandResponseDto updateFindMembers(String bandName, Long id, BandRequestDto dto);
    BandResponseDto updateBiography(String bandName, Long id, BandRequestDto dto);

    // AUXILIARES
    Band findBandByIdOrBandname(String bandName, Long id);
}
