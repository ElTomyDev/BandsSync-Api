package com.heavydelay.BandsSync.Api.repository.band;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.model.entity.Band;

public interface BandRepository extends CrudRepository<Band, Long>{
    Optional<Band> findByBandName(String bandName);
}
