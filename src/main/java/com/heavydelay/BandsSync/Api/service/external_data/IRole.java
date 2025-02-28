package com.heavydelay.BandsSync.Api.service.external_data;

import com.heavydelay.BandsSync.Api.model.dto.external_data.role.RoleRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.role.RoleResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Role;

public interface IRole {
    Role getNoneRole();
    
    RoleResponseDto showRole(String roleName, Integer idRole, Boolean detailed);

    RoleResponseDto createNewRole(RoleRequestDto dto);

    RoleResponseDto updateRoleName(String roleName, Integer idRole, RoleRequestDto dto);

    void deleteRole(String roleName, Integer idRole);

    Role findRoleByNameOrId(String roleName, Integer idRole);

}
