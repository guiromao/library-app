package co.guiromao.libraryapp.controllers;

import static co.guiromao.libraryapp.converters.BookConverter.dtoToBook;
import static co.guiromao.libraryapp.converters.BookConverter.bookToDto;
import co.guiromao.libraryapp.dto.BookDto;
import co.guiromao.libraryapp.dto.LendObjectDto;
import co.guiromao.libraryapp.models.Book;
import co.guiromao.libraryapp.models.LendObject;
import co.guiromao.libraryapp.services.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/library")
public class BooksController {

    private BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Book>> fetchAllBooks() {
        return new ResponseEntity<>(booksService.fetchAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable String isbn) {
        Optional<Book> maybeBook = booksService.findByIsbn(UUID.fromString(isbn));

        ResponseEntity<BookDto> response = (maybeBook.isPresent()) ?
                new ResponseEntity<>(bookToDto(maybeBook.get()), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return response;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto) {
        Book book = dtoToBook(bookDto);
        BookDto dto = bookToDto(booksService.saveBook(book));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/{isbn}/lend")
    public ResponseEntity lendBook(@PathVariable String isbn, @RequestParam Long memberId) {
        ResponseEntity response = (booksService.lendBookToMember(UUID.fromString(isbn), memberId)) ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.BAD_REQUEST);

        return response;
    }

    @PostMapping("/{isbn}/return")
    public ResponseEntity returnBook(@PathVariable String isbn, @RequestParam Long memberId) {
        ResponseEntity response = (booksService.returnBook(UUID.fromString(isbn), memberId)) ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.BAD_REQUEST);

        return response;
    }

    @GetMapping("/{isbn}/listlends")
    public ResponseEntity<List<LendObjectDto>> getLends(@PathVariable String isbn) {
        return new ResponseEntity<>(booksService.getLends(UUID.fromString(isbn)), HttpStatus.OK);
    }

}
