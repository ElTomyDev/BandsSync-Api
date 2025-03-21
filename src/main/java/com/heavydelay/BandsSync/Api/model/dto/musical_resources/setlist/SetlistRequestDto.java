package com.heavydelay.BandsSync.Api.model.dto.musical_resources.setlist;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SetlistRequestDto {
    public interface UpdateInUseView {}
    public interface UpdateNameView {}
    public interface CreateSetlistView extends UpdateNameView {}
    
    @JsonView(UpdateInUseView.class)
    @NotBlank(groups = {UpdateNameView.class}, message = "Setlist name must not be blank")
    @Size(min = 3, max = 100, message = "Setlist name It must not exceed 100 characters and must have a minimum of 3")
    private String setlistName;
    
    @JsonView(UpdateInUseView.class)
    @NotNull(groups = {UpdateInUseView.class}, message = "In use field must not be empty")
    private Boolean inUse;
}
