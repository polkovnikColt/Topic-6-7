package ua.polkovnik.SpringBootApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.polkovnik.SpringBootApp.models.Book;
import ua.polkovnik.SpringBootApp.service.BookRepository;
import ua.polkovnik.SpringBootApp.service.BookService;

import java.util.Collection;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	public boolean addBook(final Book bookToSave) {
		Book book =  bookRepository.save(bookToSave);
		if(!book.getTitle().equals("")){
			return true;
		}
		return false;
	}

	public Optional<Book> getBookByIsbn(String isbn) {
		return bookRepository.findByIsbn(isbn);
	}

	public Collection<Book> getAll() {
		return bookRepository.findAll();
	}

	@Override
	public boolean isBookAlreadyExists(String isbn) {
		return bookRepository.findByIsbn(isbn).isPresent();
	}
}
