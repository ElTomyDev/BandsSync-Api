package com.heavydelay.BandsSync.Api.model.dto.role;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class RoleResponseDto {

    private Integer idRole;

    private String roleName;
    
}
