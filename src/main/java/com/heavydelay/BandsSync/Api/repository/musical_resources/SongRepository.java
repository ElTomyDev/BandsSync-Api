package com.heavydelay.BandsSync.Api.repository.musical_resources;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.BandsSync.Api.model.entity.Song;

public interface SongRepository extends CrudRepository<Song, Long>{

}
