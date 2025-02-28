package com.heavydelay.BandsSync.Api.service.external_data.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heavydelay.BandsSync.Api.exception.ResourceNotFoundException;
import com.heavydelay.BandsSync.Api.model.dto.external_data.role.RoleRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.role.RoleResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Role;
import com.heavydelay.BandsSync.Api.model.mapper.external_data.IRoleMapper;
import com.heavydelay.BandsSync.Api.repository.external_data.RoleRepository;
import com.heavydelay.BandsSync.Api.service.external_data.IRole;

@Service
public class RoleImplService implements IRole{

    private RoleRepository roleRepository;
    private IRoleMapper roleMapper;

    public RoleImplService(RoleRepository roleRepository, IRoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    //////////////// CREATE METHODS ////////////////////////////
    @Override
    public RoleResponseDto createNewRole(RoleRequestDto dto) {
        Role newRole = Role.builder().roleName(dto.getRoleName()).build();

        roleRepository.save(newRole);
        return roleMapper.toBasicDto(newRole);
    }

    //////////// DELETE METHODS ////////////////////////////////
    @Override
    public void deleteRole(String roleName, Integer idRole) {
        Role roleDelete = this.findRoleByNameOrId(roleName, idRole);

        roleRepository.delete(roleDelete);
    }

    /////////////// EXTERNAL LOGIC METHODS ///////////////////////////////////////
    @Override
    public Role getNoneRole() {
        return this.findRoleByNameOrId("None", null);
    }

    @Override
    public Role getRoleByName(String roleName){
        return this.findRoleByNameOrId(roleName, null);
    }
    
    ////////////////// SHOW METHODS //////////////////////////////////
    @Override
    public RoleResponseDto showRole(String roleName, Integer idRole) {
        Role role = this.findRoleByNameOrId(roleName, idRole);

        return roleMapper.toBasicDto(role);
    }

    @Override
    public List<RoleResponseDto> showAllRoles(){
        List<Role> roles = (List<Role>) roleRepository.findAll();

        // Retorno y mapea la lista con todos los usuarios
        return roles.stream().map(roleMapper::toBasicDto).collect(Collectors.toList());
    }

    //////////////// UPDATE METHODS ////////////////////////////////////////
    @Override
    public RoleResponseDto updateRoleName(String roleName, Integer idRole, RoleRequestDto dto) {
        Role roleUpdate = this.findRoleByNameOrId(roleName, idRole);

        if (!dto.getRoleName().isEmpty()){
            roleUpdate.setRoleName(dto.getRoleName());
        }

        return roleMapper.toBasicDto(roleUpdate);
    }

    /////////////// AUXILIARES ///////////////////////////
    @Override
    public Role findRoleByNameOrId(String roleName, Integer idRole) {
        Role role;

        if(roleName != null){
            role = roleRepository.findByRoleName(roleName).orElseThrow(
                () -> new ResourceNotFoundException("The Role with name '" + roleName + "' was not found")
            );
        } else if (idRole != null){
            role = roleRepository.findById(idRole).orElseThrow(
                () -> new ResourceNotFoundException("The Role with ID '" + idRole + "' was not found")
            );
        } else{
            throw new IllegalArgumentException("Both roleName and idRole cannot be null");
        }

        return role;
    }
    
}
