package ua.polkovnik.SpringBootApp.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ua.polkovnik.SpringBootApp.models.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {

	Optional<Book> findByIsbn(String isbn);

}
