package com.heavydelay.BandsSync.Api.repository.band;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.model.entity.BandMember;

public interface BandMemberRepository extends CrudRepository<BandMember, Long>{
    
}