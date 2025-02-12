package com.heavydelay.BandsSync.Api.repository.external_data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.model.entity.Role;


public interface RoleRepository extends CrudRepository<Role, Integer>{
    Optional<Role> findByRoleName(String roleName);
}
