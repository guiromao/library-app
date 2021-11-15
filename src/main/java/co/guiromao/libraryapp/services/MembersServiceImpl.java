package co.guiromao.libraryapp.services;

import co.guiromao.libraryapp.exceptions.InvalidMemberException;
import co.guiromao.libraryapp.models.Member;
import co.guiromao.libraryapp.repositories.MembersRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MembersServiceImpl implements MembersService {

    private MembersRepository membersRepository;

    public MembersServiceImpl(MembersRepository membersRepo) {
        membersRepository = membersRepo;
    }

    @Override
    public List<Member> findAll() {
        return membersRepository.findAll();
    }

    @Override
    public Optional<Member> findById(Long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Member ID must be a positive integer.");
        }

        return membersRepository.findById(id);
    }

    @Override
    public Member saveMember(Member member) {
        if (member == null) {
            throw new InvalidMemberException("Invalid member given to save.");
        }

        return membersRepository.saveAndFlush(member);
    }

    @Override
    public void deleteMember(Long memberId) {
        if (memberId < 1) {
            throw new IllegalArgumentException("MemberId must be a positive integer number.");
        }

        membersRepository.deleteById(memberId);
    }

    @Override
    public void toggleActivity(Long memberId) {
        if (memberId < 1) {
            throw new IllegalArgumentException("MemberId must be a positive integer number.");
        }

        Optional<Member> maybeMember = findById(memberId);

        if (maybeMember.isEmpty()) {
            throw new InvalidMemberException("Member not found");
        }

        Member member = maybeMember.get();
        persistNewActivity(member);
    }

    private void persistNewActivity(Member member) {
        member.toggleActive();
        member.getActivePeriods().put(new Date(), member.isActive());
        saveMember(member);
    }

}
