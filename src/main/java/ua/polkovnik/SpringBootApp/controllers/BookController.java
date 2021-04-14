package ua.polkovnik.SpringBootApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.polkovnik.SpringBootApp.models.CommonUser;
import ua.polkovnik.SpringBootApp.service.BookService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String homePage(Model model){ return "main"; }

    @GetMapping("/book/{isbn}")
    public String bookPage(@PathVariable String isbn, Model model) {
        model.addAttribute("book", bookService.getBookByIsbn(isbn).get());
        return "bookPage";
    }

    @GetMapping("/login")
    public String registration(Model model) {
        CommonUser user = new CommonUser();
        model.addAttribute("user", user);
        return "loginPage";
    }

    @GetMapping("/favourite")
    public String favourite(Model model) {
        CommonUser user = new CommonUser();
        model.addAttribute("user", user);
        return "favouritePage";
    }

    @GetMapping("/error")
    public String error(Model model){
        return"error";
    }



}
