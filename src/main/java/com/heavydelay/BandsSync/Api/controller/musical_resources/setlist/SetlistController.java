package com.heavydelay.BandsSync.Api.controller.musical_resources.setlist;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.BandsSync.Api.model.dto.musical_resources.setlist.SetlistRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.musical_resources.setlist.SetlistResponseDto;
import com.heavydelay.BandsSync.Api.model.payload.MessageResponse;
import com.heavydelay.BandsSync.Api.service.musical_resources.setlist.ISetlist;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/setlist")
public class SetlistController {

    private ISetlist setlistService;

    ///// SHOW ENDPOINTS ///////////////////
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

    @GetMapping("/setlists/band-id/{idBand}/{detailed}")
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

    @GetMapping("/setlists/use/{inUse}/{detailed}")
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

    ///// DELETE ENDPOINTS /////////////////////////
    @DeleteMapping("/delete/id/{idSetlist}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long idSetlist){
        setlistService.deteleSetlist(idSetlist, null, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Setlist successfully delete")
            .status(HttpStatus.OK.value())
            .objectResponse("Setlist ID: "+ idSetlist)
            .build(), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/band-id/{idBand}/setlist-name/{setlistName}")
    public ResponseEntity<MessageResponse> deleteByBandAndName(@PathVariable Long idBand, @PathVariable String setlistName){
        setlistService.deteleSetlist(null, idBand, setlistName);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Setlist successfully delete")
            .status(HttpStatus.OK.value())
            .objectResponse("Band ID: " + idBand + " / Setlist Name: " + setlistName)
            .build(), HttpStatus.OK
        );
    }

    //// CREATE ENPOINTS //////////////////
    @PostMapping("/create/band-id/{idBand}")
    public ResponseEntity<MessageResponse> createSetlist(@PathVariable Long idBand, @RequestBody SetlistRequestDto dto){
        SetlistResponseDto newSetlist = setlistService.createSetlist(idBand, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Setlist successfully create")
            .status(HttpStatus.CREATED.value())
            .objectResponse(newSetlist)
            .build(), HttpStatus.CREATED
        );
    }

    //// UPDATE ENPOINTS /////////
}
