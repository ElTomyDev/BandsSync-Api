package com.heavydelay.BandsSync.Api.service.musical_resources.setlist;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.setlist.SetlistResponseDto;

public interface ISetlist {
    List<SetlistResponseDto> showAllSetlist(Long bandId, Boolean inUse, boolean detailed);
    SetlistResponseDto showSetlist(Long idSetlist, Long idBand, String setlistName, boolean detailed);

    
}
