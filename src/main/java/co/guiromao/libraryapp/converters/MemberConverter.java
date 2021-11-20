package co.guiromao.libraryapp.converters;

import co.guiromao.libraryapp.dto.BookDto;
import co.guiromao.libraryapp.dto.MemberDto;
import co.guiromao.libraryapp.models.Book;
import co.guiromao.libraryapp.models.Member;
import co.guiromao.libraryapp.utils.ModelMapperClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MemberConverter {

    private static ModelMapper modelMapper = ModelMapperClass.getInstance();

    public static Member dtoToMember(MemberDto dto) {
        return modelMapper.map(dto, Member.class);
    }

    public static MemberDto memberToDto(Member member) {
        MemberDto dto = modelMapper.map(member, MemberDto.class);

        if (member.getBooksGot() != null) {
            Set<UUID> listIsbns = new HashSet<>();
            for (Book book: member.getBooksGot()) {
                UUID uuid = book.getIsbn();
                listIsbns.add(uuid);
            }
            dto.setBooksGot(listIsbns);
        }

        return dto;
    }

}
