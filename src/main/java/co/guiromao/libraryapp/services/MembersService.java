package co.guiromao.libraryapp.services;

import co.guiromao.libraryapp.models.Member;

import java.util.List;
import java.util.Optional;

public interface MembersService {

    List<Member> findAll();

    Optional<Member> findById(Long id);

    Member saveMember(Member member);

    void deleteMember(Long memberId);

}
