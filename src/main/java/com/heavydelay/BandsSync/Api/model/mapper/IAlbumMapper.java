package com.heavydelay.BandsSync.Api.model.mapper;

import com.heavydelay.BandsSync.Api.model.dto.album.AlbumResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Album;

public interface IAlbumMapper {
    AlbumResponseDto toBasicDto(Album album);
    AlbumResponseDto toDetailedDto(Album album);
}
