package com.heavydelay.BandsSync.Api.model.mapper.external_data.impl;

import org.springframework.stereotype.Component;

import com.heavydelay.BandsSync.Api.model.dto.external_data.role.RoleResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Role;
import com.heavydelay.BandsSync.Api.model.mapper.external_data.IRoleMapper;

@Component
public class RoleImplMapper implements IRoleMapper{

    @Override
    public RoleResponseDto toBasicDto(Role role) {
        return RoleResponseDto.builder()
               .idRole(role.getIdRole())
               .roleName(role.getRoleName())
               .build();
    }

}
