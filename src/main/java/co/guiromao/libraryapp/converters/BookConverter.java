package co.guiromao.libraryapp.converters;

import co.guiromao.libraryapp.dto.BookDto;
import co.guiromao.libraryapp.models.Book;
import org.modelmapper.ModelMapper;

public class BookConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static Book dtoToBook(BookDto dto) {
        return modelMapper.map(dto, Book.class);
    }

    public static BookDto bookToDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }
}
