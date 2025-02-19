package com.heavydelay.BandsSync.Api.repository.band;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.BandMember;
import com.heavydelay.BandsSync.Api.model.entity.User;

public interface BandMemberRepository extends CrudRepository<BandMember, Long>{
    Optional<BandMember> findByBand(Band band);
    Optional<BandMember> findByUser(User user);
}