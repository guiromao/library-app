package co.guiromao.libraryapp.services;

import co.guiromao.libraryapp.exceptions.InvalidBookException;
import co.guiromao.libraryapp.models.Book;
import co.guiromao.libraryapp.models.LendObject;
import co.guiromao.libraryapp.models.Member;
import co.guiromao.libraryapp.repositories.BooksRepository;
import co.guiromao.libraryapp.repositories.MembersRepository;
import co.guiromao.libraryapp.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BooksServiceImpl implements BooksService {

    private BooksRepository booksRepository;
    private MembersRepository membersRepository;

    public BooksServiceImpl(BooksRepository booksRepo, MembersRepository membersRepo) {
        booksRepository = booksRepo;
        membersRepository = membersRepo;
    }

    @Override
    public List<Book> fetchAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public Optional<Book> findByIsbn(UUID isbn) {
        if (isbn == null || isbn.toString().isEmpty()) {
            throw new InvalidBookException("ISBN must have a valid value.");
        }

        return booksRepository.findById(isbn);
    }

    @Override
    public Book saveBook(Book book) {
        if (book.getTitle() == null) {
            throw new InvalidBookException("Book must have a title.");
        }

        return booksRepository.saveAndFlush(book);
    }

    @Override
    public void deleteByIsbn(UUID isbn) {
        if (isbn == null) {
            throw new InvalidBookException("ISBN must have a value.");
        }

        booksRepository.deleteById(isbn);
    }

    @Override
    public boolean lendBookToMember(UUID isbn, Long memberId) {
        if (isbn == null || memberId == null || memberId < 1) {
            throw new IllegalArgumentException("Invalid arguments provided.");
        }

        Optional<Book> maybeBook = booksRepository.findById(isbn);
        Optional<Member> maybeMember = membersRepository.findById(memberId);

        if (maybeBook.isEmpty() || maybeMember.isEmpty()) {
            return false;
        }

        Book book = maybeBook.get();
        Member member = maybeMember.get();

        if (book.isLent() || book.getCurrentMember() != null) {
            return false;
        }

        assignBookToMember(book, member);
        saveBook(book);
        member.addBookToCurrent(book);
        membersRepository.saveAndFlush(member);

        return true;
    }

    @Override
    public boolean returnBook(UUID isbn, Long memberId) {
        if (isbn == null || memberId == null || memberId < 1) {
            throw new IllegalArgumentException("Invalid arguments provided.");
        }

        Optional<Book> maybeBook = booksRepository.findById(isbn);
        Optional<Member> maybeMember = membersRepository.findById(memberId);

        if (maybeBook.isEmpty() || maybeMember.isEmpty()) {
            return false;
        }

        Book book = maybeBook.get();
        Member member = maybeMember.get();

        if (!book.isLent() || book.getCurrentMember() == null) {
            return false;
        }

        returnBookFromMember(book, member);
        saveBook(book);
        member.removeBookFromCurrent(book);
        membersRepository.saveAndFlush(member);

        return true;
    }

    private void assignBookToMember(Book book, Member member) {
        book.setLent(true);
        book.setCurrentMember(member);
        book.getLentList().add(createNewLendItem(member));
    }

    private void returnBookFromMember(Book book, Member member) {
        book.setLent(false);
        book.setCurrentMember(null);

        int lendsSize = book.getLentList().size();
        book.getLentList().get(lendsSize - 1).setFinalDate(new Date());
    }

    private LendObject createNewLendItem(Member member) {
        LendObject lendObject = new LendObject();
        lendObject.setMember(member);
        lendObject.setInitialDate(new Date());

        return lendObject;
    }

}
