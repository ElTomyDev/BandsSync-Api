package com.heavydelay.BandsSync.Api.repository;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.model.entity.UserEmail;

public interface UserEmailRepository extends CrudRepository<UserEmail, Long>{
    
}
