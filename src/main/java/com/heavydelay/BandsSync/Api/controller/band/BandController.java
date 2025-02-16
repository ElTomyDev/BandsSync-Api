package com.heavydelay.BandsSync.Api.controller.band;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.BandsSync.Api.model.dto.band.BandResponseDto;
import com.heavydelay.BandsSync.Api.model.payload.MessageResponse;
import com.heavydelay.BandsSync.Api.service.band.IBand;

@RestController
@RequestMapping("/band")
public class BandController {

    @Autowired
    private IBand bandService;

    ////// GET METHOD ///////////////////////////////////////////////////
    @GetMapping("/bands/{detailed}")
    public ResponseEntity<MessageResponse> showAllBands(@PathVariable boolean detailed){
        List<BandResponseDto> bands = bandService.showAllBands(detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Users successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(bands)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-id/{id}/{detailed}")
    public ResponseEntity<MessageResponse> showAllBands(@PathVariable Long id, @PathVariable boolean detailed){
        BandResponseDto band = bandService.showBand(null, id, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-bandName/{bandName}/{detailed}")
    public ResponseEntity<MessageResponse> showAllBands(@PathVariable String bandName, @PathVariable boolean detailed){
        BandResponseDto band = bandService.showBand(bandName, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    ////// POST METHOD //////////////////////////////////////////////////
    ////// DELETE METHOD ////////////////////////////////////////////////
    ////// PUT METHODS BY BAND NAME /////////////////////////////////////
    ////// PUT METHODS BY ID ////////////////////////////////////////////
}
