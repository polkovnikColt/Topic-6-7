package ua.polkovnik.SpringBootApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.polkovnik.SpringBootApp.service.BookService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String homePage(Model model){ return "main"; }

    @GetMapping("/book/{isbn}")
    public String bookPage(@PathVariable String isbn, Model model) {
        System.out.println(123 + "" + isbn);
        model.addAttribute("book", bookService.getBookByIsbn(isbn).get());
        return "bookPage";
    }

    @GetMapping("/error")
    public String error(Model model){
        return"error";
    }



}
