package co.guiromao.libraryapp.converters;

import co.guiromao.libraryapp.dto.BookDto;
import co.guiromao.libraryapp.dto.LendObjectDto;
import co.guiromao.libraryapp.models.Book;
import co.guiromao.libraryapp.models.LendObject;
import co.guiromao.libraryapp.utils.DateUtils;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static Book dtoToBook(BookDto dto) {
        return modelMapper.map(dto, Book.class);
    }

    public static BookDto bookToDto(Book book) {
        BookDto dto = modelMapper.map(book, BookDto.class);

        List<LendObjectDto> lendDtos = new ArrayList<>();

        for (LendObject obj: book.getLentList()) {
            String initDate = generateDate(obj.getInitialDate());
            String endDate = generateDate(obj.getFinalDate());
            LendObjectDto lendDto = new LendObjectDto(obj.getMember(), initDate, endDate);

            lendDtos.add(lendDto);
        }

        dto.setLendObjects(lendDtos);

        return dto;
    }

    private static String generateDate(Date date) {
        return DateUtils.dateToString(date);
    }
}
