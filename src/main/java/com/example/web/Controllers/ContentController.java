package com.example.web.Controllers;

import com.example.web.Config.Entity.Book;
import com.example.web.Config.Entity.User;
import com.example.web.Services.BookService;
import com.example.web.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContentController {

    private final BookService bookService;
    private final UserService userService;

    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/book/{id}/edit")
    public String bookEdit(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "rename-book";
    }

    @PostMapping("/book/{id}/edit")
    public String bookEdit(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getAllBook(Model model, Principal principal) {
        //User user = userService.userByName(principal.getName());
        List<Book> books = bookService.findAllByUserAndStatus(6L,1L);
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("/profile")
    public String getUser(Model model) {
        return "profile";
    }

    @GetMapping("/add-book")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "home";
    }
}
