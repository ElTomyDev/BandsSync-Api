package com.heavydelay.BandsSync.Api.service.external_data;

import java.util.List;

import com.heavydelay.BandsSync.Api.model.dto.external_data.role.RoleRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.role.RoleResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Role;

public interface IRole {

    Role getNoneRole();
    Role getRoleByName(String roleName);
    
    RoleResponseDto showRole(String roleName, Integer idRole);
    List<RoleResponseDto> showAllRoles();

    RoleResponseDto createNewRole(RoleRequestDto dto);

    void deleteRole(String roleName, Integer idRole);

    RoleResponseDto updateRoleName(String roleName, Integer idRole, RoleRequestDto dto);


    Role findRoleByNameOrId(String roleName, Integer idRole);

}
