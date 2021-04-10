package ua.polkovnik.SpringBootApp.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.polkovnik.SpringBootApp.models.Book;
import ua.polkovnik.SpringBootApp.service.BookService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class BookRest {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public @ResponseBody Collection<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/getOneBook/{param}")
    public @ResponseBody Book getOneBook(@PathVariable String param){
        Collection<Book> books =  bookService.getAll();
        for(Book book : books){
            if(book.getTitle().equals(param) || book.getIsbn().equals(param)){
                return book;
            }
        }
        return null;
    }

//    @GetMapping("/book/{isbn}")
//    public ResponseEntity<?> getById(@PathVariable("isbn") String isbn) {
//        Optional<Book> book = bookService.getBookByIsbn(isbn);
//        return book.isPresent()
//                ? ResponseEntity.ok(book.get())
//                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    }

    @PostMapping("/addBook")
    public @ResponseBody Book addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return savedBook;
    }
}
