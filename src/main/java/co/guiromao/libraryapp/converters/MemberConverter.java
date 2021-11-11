package co.guiromao.libraryapp.converters;

import co.guiromao.libraryapp.dto.BookDto;
import co.guiromao.libraryapp.dto.MemberDto;
import co.guiromao.libraryapp.models.Book;
import co.guiromao.libraryapp.models.Member;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

public class MemberConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static Member dtoToMember(MemberDto dto) {
        Member member = modelMapper.map(dto, Member.class);

        if (member.getBooksGot() != null) {
            Set<Book> listBooks = new HashSet<>();
            for (BookDto bookDto : dto.getBooksGot()) {
                Book book = BookConverter.dtoToBook(bookDto);
                listBooks.add(book);
            }
            member.setBooksGot(listBooks);
        }

        return member;
    }

    public static MemberDto memberToDto(Member member) {
        MemberDto dto = modelMapper.map(member, MemberDto.class);

        if (member.getBooksGot() != null) {
            Set<BookDto> listBooks = new HashSet<>();
            for (Book book: member.getBooksGot()) {
                BookDto bookDto = BookConverter.bookToDto(book);
                listBooks.add(bookDto);
            }
            dto.setBooksGot(listBooks);
        }

        return dto;
    }

}
