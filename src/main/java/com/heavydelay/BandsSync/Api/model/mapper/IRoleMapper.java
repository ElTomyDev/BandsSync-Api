package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.role.RoleResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Role;

public interface IRoleMapper {
    RoleResponseDto toBasicDto(Role role);
}
