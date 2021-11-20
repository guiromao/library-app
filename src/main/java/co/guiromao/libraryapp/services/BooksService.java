package co.guiromao.libraryapp.services;

import co.guiromao.libraryapp.dto.BookDto;
import co.guiromao.libraryapp.dto.LendObjectDto;
import co.guiromao.libraryapp.models.Book;
import co.guiromao.libraryapp.models.LendObject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BooksService {

    List<Book> fetchAllBooks();

    Optional<Book> findByIsbn(UUID isbn);

    Book saveBook(Book book);

    void deleteByIsbn(UUID isbn);

    boolean lendBookToMember(UUID isbn, Long memberId);

    boolean returnBook(UUID isbn, Long memberId);

    List<LendObjectDto> getLends(UUID isbn);

}
