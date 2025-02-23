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
import com.heavydelay.BandsSync.Api.model.dto.band.band_member.BandMemberRequestDto;
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
        List<BandMemberResponseDto> members = memberService.showAllMembers(detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Members successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(members)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/members-by-band-id/{id}/{detailed}")
    public ResponseEntity<MessageResponse> showAllMembersByBandId(@PathVariable Long id, @PathVariable boolean detailed){
        List<BandMemberResponseDto> members = memberService.showAllMembersByBand(null, id, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("All band members with the ID '"+ id +"' obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(members)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/members-by-band-name/{bandName}/{detailed}")
    public ResponseEntity<MessageResponse> showAllMembersByBandName(@PathVariable String bandName, @PathVariable boolean detailed){
        List<BandMemberResponseDto> members = memberService.showAllMembersByBand(bandName, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("All band members with the name '"+ bandName +"' obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(members)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-id/{id}/{detailed}")
    public ResponseEntity<MessageResponse> showMemberById(@PathVariable Long id, @PathVariable boolean detailed){
        BandMemberResponseDto member = memberService.showMember(null, null, null, null, id, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with ID '"+ id +"' successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(member)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-user-id/{idUser}/{detailed}")
    public ResponseEntity<MessageResponse> showMemberByUserId(@PathVariable Long idUser, @PathVariable boolean detailed){
        BandMemberResponseDto member = memberService.showMember(null, null, null, idUser, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with user ID '"+ idUser +"' successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(member)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-band-id/{idBand}/{detailed}")
    public ResponseEntity<MessageResponse> showMemberByBandId(@PathVariable Long idBand, @PathVariable boolean detailed){
        BandMemberResponseDto member = memberService.showMember(null, null, idBand, null, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with band ID '"+ idBand +"' successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(member)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-band-name/{bandName}/{detailed}")
    public ResponseEntity<MessageResponse> showMemberByBandName(@PathVariable String bandName, @PathVariable boolean detailed){
        BandMemberResponseDto member = memberService.showMember(null, bandName, null, null, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with band name '"+ bandName +"' successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(member)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-username/{username}/{detailed}")
    public ResponseEntity<MessageResponse> showMemberByUsername(@PathVariable String username, @PathVariable boolean detailed){
        BandMemberResponseDto member = memberService.showMember(username, null, null, null, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with username '"+ username +"' successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(member)
            .build(), HttpStatus.OK
        );
    }
    
    ////// DELETE ENDPOINTS ///////////////////////////////////////////////
    @DeleteMapping("/delete-by/{id}")
    public ResponseEntity<MessageResponse> deleteMemberById(@PathVariable Long id){
        memberService.deleteMember(null, null, null, null, id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with ID '"+ id +"' successfully deleted.")
            .status(HttpStatus.OK.value())
            .objectResponse("ID MEMBER: " + id)
            .build(), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-by/{idUser}")
    public ResponseEntity<MessageResponse> deleteMemberByUserId(@PathVariable Long idUser){
        memberService.deleteMember(null, null, null, idUser, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with user ID '"+ idUser +"' successfully deleted.")
            .status(HttpStatus.OK.value())
            .objectResponse("ID USER: " + idUser)
            .build(), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-by/{idBand}")
    public ResponseEntity<MessageResponse> deleteMemberByBandId(@PathVariable Long idBand){
        memberService.deleteMember(null, null, idBand, null, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with band ID '"+ idBand +"' successfully deleted.")
            .status(HttpStatus.OK.value())
            .objectResponse("ID BAND: " + idBand)
            .build(), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-by/{bandName}")
    public ResponseEntity<MessageResponse> deleteMemberByBandName(@PathVariable String bandName){
        memberService.deleteMember(null, bandName, null, null, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with band name '"+ bandName +"' successfully deleted.")
            .status(HttpStatus.OK.value())
            .objectResponse("BAND NAME: " + bandName)
            .build(), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-by/{username}")
    public ResponseEntity<MessageResponse> deleteMemberByUsername(@PathVariable String username){
        memberService.deleteMember(username, null, null, null, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with username '"+ username +"' successfully deleted.")
            .status(HttpStatus.OK.value())
            .objectResponse("USERNAME: " + username)
            .build(), HttpStatus.OK
        );
    }
    
    ///// POST ENDPOINTS ////////////////////////////////////////////
    @JsonView(BandMemberRequestDto.JoinBandView.class)
    @PostMapping("/join-by-username/{username}")
    public ResponseEntity<MessageResponse> joinBandByUsername(@PathVariable String username, @RequestBody BandMemberRequestDto dto){
        BandMemberResponseDto newMember = memberService.joinBand(username, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with username '"+ username +"' successfully created and joined the band with ID '"+ dto.getIdBand() + "'.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(newMember)
            .build(), HttpStatus.CREATED
        );
    }

    @JsonView(BandMemberRequestDto.JoinBandView.class)
    @PostMapping("/join-by-id/{id}")
    public ResponseEntity<MessageResponse> joinBandByUsername(@PathVariable Long id, @RequestBody BandMemberRequestDto dto){
        BandMemberResponseDto newMember = memberService.joinBand(null, id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with USER ID '"+ id +"' successfully created and joined the band with ID '"+ dto.getIdBand() + "'.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(newMember)
            .build(), HttpStatus.CREATED
            );
    }
        
    //////// PUT ENDPOINTS ///////////////////////////////////////////
    @JsonView(BandMemberRequestDto.FindByUserAndBandView.class)
    @PutMapping("/leave-band")
    public ResponseEntity<MessageResponse> leaveBand(@RequestBody BandMemberRequestDto dto){
        BandMemberResponseDto member = memberService.leaveBand(dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member '"+ dto.getUsername() +"' of band '"+ dto.getBandName() + "' has left the group.")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(member)
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @JsonView({BandMemberRequestDto.FindByUserAndBandView.class, BandMemberRequestDto.RoleNameView.class})
    @PutMapping("/update-member-role")
    public ResponseEntity<MessageResponse> updateRole(@RequestBody BandMemberRequestDto dto){
        BandMemberResponseDto member = memberService.updateRole(dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member role '"+ dto.getUsername() +"' updated successfully")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(member)
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @JsonView({BandMemberRequestDto.FindByUserAndBandView.class, BandMemberRequestDto.RoleNameView.class})
    @PutMapping("/update-admin/{username}/{bandName}/{isAdmin}")
    public ResponseEntity<MessageResponse> updateAdmin(@PathVariable String username, @PathVariable String bandName, @PathVariable Boolean isAdmin){
        BandMemberResponseDto member = memberService.updateAdmin(username, bandName, isAdmin);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member '"+ username +"' is admin updated successfully")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(member)
            .build(), HttpStatus.NO_CONTENT
        );
    }

}
