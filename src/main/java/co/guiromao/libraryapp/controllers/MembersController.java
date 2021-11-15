package co.guiromao.libraryapp.controllers;

import co.guiromao.libraryapp.converters.MemberConverter;
import co.guiromao.libraryapp.dto.MemberDto;
import co.guiromao.libraryapp.models.Member;
import co.guiromao.libraryapp.services.MembersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/library/members")
public class MembersController {

    private MembersService membersService;

    public MembersController(MembersService membersServ) {
        membersService = membersServ;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Member>> fetchAll() {
        return new ResponseEntity<>(membersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> findMember(@PathVariable Long memberId) {
        Optional<Member> maybeMember = membersService.findById(memberId);

        ResponseEntity<Member> response = maybeMember.isPresent() ?
                new ResponseEntity<>(maybeMember.get(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return response;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        Member member = MemberConverter.dtoToMember(memberDto);

        Map<Date, Boolean> activePeriods = new HashMap<>();
        activePeriods.put(new Date(), true);
        member.setActivePeriods(activePeriods);

        MemberDto respDto = MemberConverter.memberToDto(membersService.saveMember(member));
        
        return new ResponseEntity<>(respDto, HttpStatus.CREATED);
    }

    @PutMapping("/{memberId}/active")
    public ResponseEntity switchActivity(@PathVariable Long memberId) {
        membersService.toggleActivity(memberId);

        return new ResponseEntity(HttpStatus.OK);
    }

}
