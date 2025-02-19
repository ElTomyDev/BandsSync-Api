package com.heavydelay.BandsSync.Api.controller.band;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.heavydelay.BandsSync.Api.model.dto.band.BandRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.band.BandResponseDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksRequestDto;
import com.heavydelay.BandsSync.Api.model.dto.external_data.social.SocialLinksResponseDto;
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
            .message("Bands successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(bands)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-id/{id}/{detailed}")
    public ResponseEntity<MessageResponse> showBandBiId(@PathVariable Long id, @PathVariable boolean detailed){
        BandResponseDto band = bandService.showBand(null, id, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Band successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-bandname/{bandName}/{detailed}")
    public ResponseEntity<MessageResponse> showBandByBandname(@PathVariable String bandName, @PathVariable boolean detailed){
        BandResponseDto band = bandService.showBand(bandName, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Band successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    ////// POST METHOD //////////////////////////////////////////////////
    @JsonView(BandRequestDto.CreateBandView.class)
    @PostMapping("/create-band")
    public ResponseEntity<MessageResponse> createNewBand(@RequestBody BandRequestDto dto){
        BandResponseDto band = bandService.createBand(dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Band created successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(band)
            .build(), HttpStatus.CREATED
        );
    }

    ////// DELETE METHOD ////////////////////////////////////////////////
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<MessageResponse> deleteBandById(@PathVariable Long id){
        bandService.deleteBand(null, id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("band with ID '" + id + "' successfully removed")
            .status(HttpStatus.OK.value())
            .objectResponse("ID BAND: " + id)
            .build(), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-by-bandname/{bandName}")
    public ResponseEntity<MessageResponse> deleteBandByBandname(@PathVariable String bandName){
        bandService.deleteBand(bandName, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("band with band name '" + bandName + "' successfully removed")
            .status(HttpStatus.OK.value())
            .objectResponse("BAND NAME: " + bandName)
            .build(), HttpStatus.OK
        );
    }

    ////// PUT METHODS BY BAND NAME /////////////////////////////////////
    @PutMapping("/update-accesscode-by-bandname/{bandName}")
    public ResponseEntity<MessageResponse> updateAccesCodeByBandName(@PathVariable String bandName){
        BandResponseDto band = bandService.updateAccessCode(bandName, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band AccessCode field with the name '" + bandName + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(BandRequestDto.BandNameView.class)
    @PutMapping("/update-bandname-by-bandname/{bandName}")
    public ResponseEntity<MessageResponse> updateBandNameByBandName(@RequestBody BandRequestDto dto, @PathVariable String bandName){
        BandResponseDto band = bandService.updateBandName(bandName, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band BandName field with the name '" + bandName + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }
    
    @JsonView(BandRequestDto.BiographyView.class)
    @PutMapping("/update-biography-by-bandname/{bandName}")
    public ResponseEntity<MessageResponse> updateBiographyByBandName(@RequestBody BandRequestDto dto, @PathVariable String bandName){
        BandResponseDto band = bandService.updateBiography(bandName, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band Biography field with the name '" + bandName + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(BandRequestDto.FindMembersView.class)
    @PutMapping("/update-findmembers-by-bandname/{bandName}")
    public ResponseEntity<MessageResponse> updateFindMembersByBandName(@RequestBody BandRequestDto dto, @PathVariable String bandName){
        BandResponseDto band = bandService.updateFindMembers(bandName, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band FindMembers field with the name '" + bandName + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(BandRequestDto.GenderNameView.class)
    @PutMapping("/update-gender-by-bandname/{bandName}")
    public ResponseEntity<MessageResponse> updateGenderByBandName(@RequestBody BandRequestDto dto, @PathVariable String bandName){
        BandResponseDto band = bandService.updateGender(bandName, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band Gender field with the name '" + bandName + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(BandRequestDto.ImageURLView.class)
    @PutMapping("/update-imageurl-by-bandname/{bandName}")
    public ResponseEntity<MessageResponse> updateImageUrlByBandName(@RequestBody BandRequestDto dto, @PathVariable String bandName){
        BandResponseDto band = bandService.updateImageUrl(bandName, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band ImageUrl field with the name '" + bandName + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(BandRequestDto.IsSolistView.class)
    @PutMapping("/update-issolist-by-bandname/{bandName}")
    public ResponseEntity<MessageResponse> updateIsSolistByBandName(@RequestBody BandRequestDto dto, @PathVariable String bandName){
        BandResponseDto band = bandService.updateIsSolist(bandName, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band IsSolist field with the name '" + bandName + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(SocialLinksRequestDto.SocialLinksView.class)
    @PutMapping("/update-sociallinks-by-bandname/{bandName}")
    public ResponseEntity<MessageResponse> updateSocialLinksByBandName(@RequestBody SocialLinksRequestDto dto, @PathVariable String bandName){
        SocialLinksResponseDto social = bandService.updateSocialLinks(bandName, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band SocialLinks field with the name '" + bandName + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(social)
            .build(), HttpStatus.OK
        );
    }

    ////// PUT METHODS BY ID ////////////////////////////////////////////
    @PutMapping("/update-accesscode-by-id/{id}")
    public ResponseEntity<MessageResponse> updateAccesCodeById(@PathVariable Long id){
        BandResponseDto band = bandService.updateAccessCode(null, id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band AccessCode field with the ID '" + id + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(BandRequestDto.BandNameView.class)
    @PutMapping("/update-bandname-by-id/{id}")
    public ResponseEntity<MessageResponse> updateBandNameById(@RequestBody BandRequestDto dto, @PathVariable Long id){
        BandResponseDto band = bandService.updateBandName(null, id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band BandName field with the id '" + id + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }
    
    @JsonView(BandRequestDto.BiographyView.class)
    @PutMapping("/update-biography-by-id/{id}")
    public ResponseEntity<MessageResponse> updateBiographyById(@RequestBody BandRequestDto dto, @PathVariable Long id){
        BandResponseDto band = bandService.updateBiography(null, id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band Biography field with the id '" + id + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(BandRequestDto.FindMembersView.class)
    @PutMapping("/update-findmembers-by-id/{id}")
    public ResponseEntity<MessageResponse> updateFindMembersById(@RequestBody BandRequestDto dto, @PathVariable Long id){
        BandResponseDto band = bandService.updateFindMembers(null, id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band FindMembers field with the id '" + id + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(BandRequestDto.GenderNameView.class)
    @PutMapping("/update-gender-by-id/{id}")
    public ResponseEntity<MessageResponse> updateGenderById(@RequestBody BandRequestDto dto, @PathVariable Long id){
        BandResponseDto band = bandService.updateGender(null, id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band Gender field with the id '" + id + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(BandRequestDto.ImageURLView.class)
    @PutMapping("/update-imageurl-by-id/{id}")
    public ResponseEntity<MessageResponse> updateImageUrlById(@RequestBody BandRequestDto dto, @PathVariable Long id){
        BandResponseDto band = bandService.updateImageUrl(null, id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band ImageUrl field with the id '" + id + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(BandRequestDto.IsSolistView.class)
    @PutMapping("/update-issolist-by-id/{id}")
    public ResponseEntity<MessageResponse> updateIsSolistById(@RequestBody BandRequestDto dto, @PathVariable Long id){
        BandResponseDto band = bandService.updateIsSolist(null, id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band IsSolist field with the id '" + id + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @JsonView(SocialLinksRequestDto.SocialLinksView.class)
    @PutMapping("/update-sociallinks-by-id/{id}")
    public ResponseEntity<MessageResponse> updateSocialLinksById(@RequestBody SocialLinksRequestDto dto, @PathVariable Long id){
        SocialLinksResponseDto social = bandService.updateSocialLinks(null, id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("The band SocialLinks field with the id '" + id + "' updated correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(social)
            .build(), HttpStatus.OK
        );
    }

}
