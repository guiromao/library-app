package co.guiromao.libraryapp.controllers;

import co.guiromao.libraryapp.models.Member;
import co.guiromao.libraryapp.services.MembersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

}
