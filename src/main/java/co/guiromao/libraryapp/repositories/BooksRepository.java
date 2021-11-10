package co.guiromao.libraryapp.repositories;

import co.guiromao.libraryapp.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BooksRepository extends JpaRepository<Book, UUID> {
}
