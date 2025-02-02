package com.heavydelay.BandsSync.Api.model.mapper.impl.musical_resources;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.album.AlbumResponseDto;
import com.heavydelay.BandsSync.Api.model.entity.Album;
import com.heavydelay.BandsSync.Api.model.mapper.IAlbumMapper;

public class AlbumImplMapper implements IAlbumMapper{

    @Override
    public AlbumResponseDto toBasicDto(Album album) {
        return AlbumResponseDto.builder()
               .idAlbum(album.getIdAlbum())
               .bandName(album.getBand().getBandName())
               .setlistName(album.getSetlist().getSetlistName())
               .albumName(album.getAlbumName())
               .isPublic(album.getIsPublic())
               .build();
    }

    @Override
    public AlbumResponseDto toDetailedDto(Album album) {
        return AlbumResponseDto.builder()
               .idAlbum(album.getIdAlbum())
               .band(album.getBand())
               .setlist(album.getSetlist())
               .albumName(album.getAlbumName())
               .releaseDate(album.getReleaseDate())
               .coverImage(album.getCoverImage())
               .songsCount(album.getSongsCount())
               .isPublic(album.getIsPublic())
               .build();
    }

}
