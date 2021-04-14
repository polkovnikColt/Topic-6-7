package ua.polkovnik.SpringBootApp.service;

import org.springframework.stereotype.Service;
import ua.polkovnik.SpringBootApp.models.Book;

import java.util.Collection;
import java.util.Optional;

@Service
public interface BookService {

	boolean addBook(final Book bookToSave) ;

	Optional<Book> getBookByIsbn(String isbn);

	Collection<Book> getAll();

	boolean isBookAlreadyExists(String isbn);

}
