package com.heavydelay.BandsSync.Api.repository.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
