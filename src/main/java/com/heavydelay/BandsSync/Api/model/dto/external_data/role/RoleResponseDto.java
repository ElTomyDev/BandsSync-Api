package com.heavydelay.BandsSync.Api.model.dto.external_data.role;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResponseDto {

    private Integer idRole;

    private String roleName;
    
}
