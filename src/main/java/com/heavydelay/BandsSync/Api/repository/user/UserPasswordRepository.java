package com.heavydelay.BandsSync.Api.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.model.entity.UserPassword;

public interface UserPasswordRepository extends CrudRepository<UserPassword, Long>{
    
}
