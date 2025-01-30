package com.heavydelay.BandsSync.Api.model.dto.band_member;

import java.time.LocalDateTime;

import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.Role;
import com.heavydelay.BandsSync.Api.model.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class BandMemberResponseDto {
    
    private Long idBandMember;

    private User user;
    private String username;

    private Band band;
    private String bandName;

    private Role role;
    private String roleName;

    private Boolean isAdmin;

    private LocalDateTime joinDate;

    private LocalDateTime leaveDate;

}
