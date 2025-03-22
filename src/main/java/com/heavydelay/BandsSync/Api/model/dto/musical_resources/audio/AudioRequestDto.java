package com.heavydelay.BandsSync.Api.model.dto.musical_resources.audio;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AudioRequestDto {
    public interface UpdateFormatView {}
    public interface UpdateFilePathView {}
    public interface UpdateSizeView {}
    public interface UpdateBitrateView {}
    public interface UpdateCreateAudioView extends UpdateFormatView, UpdateFilePathView, UpdateBitrateView, UpdateSizeView {}

    @JsonView(UpdateFormatView.class)
    @NotBlank(groups = {UpdateFormatView.class}, message = "Format must not be blank")
    @Size(groups = {UpdateFormatView.class}, min = 2, max = 10, message = "Format It must not exceed 10 characters and must have a minimum of 2")
    private String format;
    
    @JsonView(UpdateFilePathView.class)
    @NotBlank(groups = {UpdateFilePathView.class}, message = "File path must not be blank")
    @Size(groups = {UpdateFilePathView.class}, max = 255, message = "File path must have a maximum of 255 characters")
    private String filePath;
    
    @JsonView(UpdateSizeView.class)
    @NotNull(groups = {UpdateSizeView.class}, message = "Size must not be empty")
    private Long size;
    
    @JsonView(UpdateBitrateView.class)
    @NotNull(groups = {UpdateBitrateView.class}, message = "Bitrate must not be empty")
    private Short bitrate;

}
