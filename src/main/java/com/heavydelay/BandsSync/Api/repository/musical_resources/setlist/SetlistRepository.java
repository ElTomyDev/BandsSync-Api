package com.heavydelay.BandsSync.Api.repository.musical_resources.setlist;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.model.entity.Band;
import com.heavydelay.BandsSync.Api.model.entity.Setlist;

public interface SetlistRepository extends CrudRepository<Setlist, Long>{
    Iterable<Setlist> findAllByBand(Band band);
    Iterable<Setlist> findAllByInUse(Boolean inUse);

    Optional<Setlist> findByBandAndSetlistName(Band band, String setlistName);
    
}
