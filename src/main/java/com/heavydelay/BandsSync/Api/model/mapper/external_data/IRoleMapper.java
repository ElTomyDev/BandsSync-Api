package com.heavydelay.BandsSync.Api.model.mapper.external_data;

import com.heavydelay.BandsSync.Api.model.dto.external_data.role.RoleResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Role;

public interface IRoleMapper {
    RoleResponseDto toBasicDto(Role role);
}
