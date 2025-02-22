package com.heavydelay.BandsSync.Api.controller.band;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberResponseDto;
import com.heavydelay.BandsSync.Api.model.payload.MessageResponse;
import com.heavydelay.BandsSync.Api.service.band.IBandMember;

@RestController
@RequestMapping("/member")
public class BandMemberController {
    
    @Autowired
    private IBandMember memberService;

    ///// GET ENDPOINTS //////////////////////////////////////////////////////
    @GetMapping("/members/{detailed}")
    public ResponseEntity<MessageResponse> showAllMembers(@PathVariable boolean detailed){
        List<BandMemberResponseDto> bands = memberService.showAllMembers(detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Members successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(bands)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/members-by-band-id/{id}/{detailed}")
    public ResponseEntity<MessageResponse> showAllMembersByBandId(@PathVariable Long id, @PathVariable boolean detailed){
        List<BandMemberResponseDto> bands = memberService.showAllMembersByBand(null, id, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("All band members with the ID '"+ id +"' obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(bands)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/members-by-band-name/{id}/{detailed}")
    public ResponseEntity<MessageResponse> showAllMembersByBandName(@PathVariable String bandName, @PathVariable boolean detailed){
        List<BandMemberResponseDto> bands = memberService.showAllMembersByBand(bandName, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("All band members with the name '"+ bandName +"' obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(bands)
            .build(), HttpStatus.OK
        );
    }
}
