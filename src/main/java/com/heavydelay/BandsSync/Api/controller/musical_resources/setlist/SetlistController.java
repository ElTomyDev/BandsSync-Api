package com.heavydelay.BandsSync.Api.controller.musical_resources.setlist;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.setlist.SetlistResponseDto;
import com.heavydelay.BandsSync.Api.model.payload.MessageResponse;
import com.heavydelay.BandsSync.Api.service.musical_resources.setlist.ISetlist;

@RestController
@RequestMapping("/setlist")
public class SetlistController {

    private ISetlist setlistService;

    ///// SHOW ENDPONTS ///////////////////
    @GetMapping("/setlists/{detailed}")
    public ResponseEntity<MessageResponse> showAllSetlist(@PathVariable boolean detailed){
        List<SetlistResponseDto> setlists = setlistService.showAllSetlist(null, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Setlists successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(setlists)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/setlists/by-band-id/{idBand}/{detailed}")
    public ResponseEntity<MessageResponse> showAllSetlistByBand(@PathVariable Long idBand, @PathVariable boolean detailed){
        List<SetlistResponseDto> setlists = setlistService.showAllSetlist(idBand, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Setlists successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(setlists)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/setlists/by-use/{inUse}/{detailed}")
    public ResponseEntity<MessageResponse> showAllSetlistByUse(@PathVariable Boolean inUse, @PathVariable boolean detailed){
        List<SetlistResponseDto> setlists = setlistService.showAllSetlist(null, inUse, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Setlists successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(setlists)
            .build(), HttpStatus.OK
        );
    }
}
