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
    // Show more then one
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

    // show one
    @GetMapping("/show-by-member-id/{id}/{detailed}")
    public ResponseEntity<MessageResponse> showMemberByMemberId(@PathVariable Long id, @PathVariable boolean detailed){
        BandMemberResponseDto member = memberService.showMember(null, null, null, null, id, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with ID '"+ id +"' successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(member)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-ids/{idUser}/{idBand}/{detailed}")
    public ResponseEntity<MessageResponse> showMemberById(@PathVariable Long idUser, @PathVariable Long idBand, @PathVariable boolean detailed){
        BandMemberResponseDto member = memberService.showMember(null, idUser, null, idBand, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(member)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-by-names/{username}/{bandName}/{detailed}")
    public ResponseEntity<MessageResponse> showMemberByNames(@PathVariable String username, @PathVariable String bandName, @PathVariable boolean detailed){
        BandMemberResponseDto member = memberService.showMember(username, null, bandName, null, null, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(member)
            .build(), HttpStatus.OK
        );
    }

    ////// DELETE ENDPOINTS ///////////////////////////////////////////////
    @DeleteMapping("/delete-by-member-id/{idMember}")
    public ResponseEntity<MessageResponse> deleteMemberByIdMember(@PathVariable Long idMember){
        memberService.deleteMember(null, null, null, null, idMember);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with ID '"+ idMember +"' successfully deleted.")
            .status(HttpStatus.OK.value())
            .objectResponse("ID MEMBER: " + idMember)
            .build(), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-by-ids/{idUser}/{idBand}")
    public ResponseEntity<MessageResponse> deleteMemberByIds(@PathVariable Long idUser, @PathVariable Long idBand){
        memberService.deleteMember(null, idUser, null, idBand, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member successfully deleted.")
            .status(HttpStatus.OK.value())
            .objectResponse("ID USER: " + idUser + "\n" + "ID BAND: "+ idBand)
            .build(), HttpStatus.OK
        );
    }

    @DeleteMapping("/delete-by-names/{username}/{bandName}")
    public ResponseEntity<MessageResponse> deleteMemberByNames(@PathVariable String username, @PathVariable String bandName){
        memberService.deleteMember(username, null, bandName, null, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with band name '"+ bandName +"' successfully deleted.")
            .status(HttpStatus.OK.value())
            .objectResponse("BAND NAME: " + bandName + "\n" + "USERNAME: " + username)
            .build(), HttpStatus.OK
        );
    }

    ///// POST ENDPOINTS ////////////////////////////////////////////
    @JsonView(BandMemberRequestDto.JoinBandView.class)
    @PostMapping("/join-by-id-band/{idBand}/for-user-id/{idUser}")
    public ResponseEntity<MessageResponse> joinBandByBandId(@PathVariable Long idBand, @PathVariable Long idUser, @RequestBody BandMemberRequestDto dto){
        BandMemberResponseDto newMember = memberService.joinBand(null, idUser, null, idBand, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with ID '"+ idUser +"' successfully created and joined the band with ID '"+ idBand + "'.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(newMember)
            .build(), HttpStatus.CREATED
        );
    }

    @JsonView(BandMemberRequestDto.JoinBandView.class)
    @PostMapping("/join-by-bandName/{bandName}/for-user-id/{idUser}")
    public ResponseEntity<MessageResponse> joinBandByBandName(@PathVariable String bandName, @PathVariable Long idUser, @RequestBody BandMemberRequestDto dto){
        BandMemberResponseDto newMember = memberService.joinBand(null, idUser, bandName, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with ID '"+ idUser +"' successfully created and joined the band with name '"+ bandName + "'.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(newMember)
            .build(), HttpStatus.CREATED
        );
    }

    @JsonView(BandMemberRequestDto.JoinBandView.class)
    @PostMapping("/join-by-id-band/{idBand}/for-user-username/{username}")
    public ResponseEntity<MessageResponse> joinBandByBandId(@PathVariable Long idBand, @PathVariable String username, @RequestBody BandMemberRequestDto dto){
        BandMemberResponseDto newMember = memberService.joinBand(username, null, null, idBand, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with username '"+ username +"' successfully created and joined the band with ID '"+ idBand + "'.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(newMember)
            .build(), HttpStatus.CREATED
        );
    }
     
    @JsonView(BandMemberRequestDto.JoinBandView.class)
    @PostMapping("/join-by-bandName/{bandName}/for-user-username/{username}")
    public ResponseEntity<MessageResponse> joinBandByBandName(@PathVariable String bandName, @PathVariable String username, @RequestBody BandMemberRequestDto dto){
        BandMemberResponseDto newMember = memberService.joinBand(username, null, bandName, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with username '"+ username +"' successfully created and joined the band with name '"+ bandName + "'.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(newMember)
            .build(), HttpStatus.CREATED
        );
    }

    //////// PUT ENDPOINTS ///////////////////////////////////////////
    @PutMapping("/leave-band-by-ids/{idUser}/{idBand}")
    public ResponseEntity<MessageResponse> leaveBandByIds(@PathVariable Long idUser, @PathVariable Long idBand){
        BandMemberResponseDto member = memberService.leaveBand(null, idUser, null, idBand);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with user ID '"+ idUser +"' of band with ID '"+ idBand + "' has left the group.")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(member)
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @PutMapping("/leave-band-by-names/{username}/{bandName}")
    public ResponseEntity<MessageResponse> leaveBandByNames(@PathVariable String username, @PathVariable String bandName){
        BandMemberResponseDto member = memberService.leaveBand(username, null, bandName, null);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with user name '"+ username +"' of band with name '"+ bandName + "' has left the group.")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(member)
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @JsonView({BandMemberRequestDto.RoleNameView.class})
    @PutMapping("/update-member-role-by-member-id/{idMember}")
    public ResponseEntity<MessageResponse> updateRoleByMemberId(@PathVariable Long idMember, @RequestBody BandMemberRequestDto dto){
        BandMemberResponseDto member = memberService.updateRole(null, null, null, null, idMember, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member role updated successfully")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(member)
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @JsonView({BandMemberRequestDto.RoleNameView.class})
    @PutMapping("/update-member-role-by-ids/{idUser}/{idBand}")
    public ResponseEntity<MessageResponse> updateRoleByIds(@PathVariable Long idUser, @PathVariable Long idBand, @RequestBody BandMemberRequestDto dto){
        BandMemberResponseDto member = memberService.updateRole(null, idUser, null, idBand, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member role updated successfully")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(member)
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @JsonView({BandMemberRequestDto.RoleNameView.class})
    @PutMapping("/update-member-role-by-names/{username}/{bandName}")
    public ResponseEntity<MessageResponse> updateRoleByNames(@PathVariable String username, @PathVariable String bandName, @RequestBody BandMemberRequestDto dto){
        BandMemberResponseDto member = memberService.updateRole(username, null, bandName, null, null, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member role updated successfully")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(member)
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @PutMapping("/update-admin-by-member-id/{idMember}/{isAdmin}")
    public ResponseEntity<MessageResponse> updateAdminByMemberId(@PathVariable Long idMember, @PathVariable Boolean isAdmin){
        BandMemberResponseDto member = memberService.updateAdmin(null, null, null, null, idMember, isAdmin);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with ID '"+ idMember +"' is admin updated successfully")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(member)
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @PutMapping("/update-admin-by-names/{username}/{bandName}/{isAdmin}")
    public ResponseEntity<MessageResponse> updateAdminByNames(@PathVariable String username, @PathVariable String bandName, @PathVariable Boolean isAdmin){
        BandMemberResponseDto member = memberService.updateAdmin(username, null, bandName, null, null, isAdmin);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member '"+ username +"' is admin updated successfully")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(member)
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @PutMapping("/update-admin-by-ids/{idUser}/{idBand}/{isAdmin}")
    public ResponseEntity<MessageResponse> updateAdminByIds(@PathVariable Long idUser, @PathVariable Long idBand, @PathVariable Boolean isAdmin){
        BandMemberResponseDto member = memberService.updateAdmin(null, idUser, null, idBand, null, isAdmin);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Member with user ID '"+ idUser +"' is admin updated successfully")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(member)
            .build(), HttpStatus.NO_CONTENT
        );
    }

}
