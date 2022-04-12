package com.example.web.Controllers;

import com.example.web.Config.Entity.Book;
import com.example.web.Services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ContentController {

    private final BookService bookService;

    @GetMapping("/book/{id}")
    public String getMovieById(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book";
    }
}
