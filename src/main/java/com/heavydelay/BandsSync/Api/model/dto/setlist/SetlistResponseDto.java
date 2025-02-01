package com.heavydelay.BandsSync.Api.model.dto.setlist;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.BandsSync.Api.model.entity.Band;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetlistResponseDto {
    private Long idSetlist;

    private Band band;
    private String bandName;
    
    private String setlistName;
    
    private LocalDateTime createDate;

    private Boolean inUse;
}
