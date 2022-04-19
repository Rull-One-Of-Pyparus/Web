package com.example.web.Controllers;

import com.example.web.Config.Entity.Book;
import com.example.web.Config.Entity.Library;
import com.example.web.Config.Entity.User;
import com.example.web.Services.BookService;
import com.example.web.Services.LibraryService;
import com.example.web.Services.StatusService;
import com.example.web.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContentController {

    private final BookService bookService;
    private final UserService userService;
    private final LibraryService libraryService;
    private final StatusService statusService;


    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("library", new Library());
        return "book";
    }

    @GetMapping("/book/{id}/edit-admin")
    public String bookEdit(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "rename-book";
    }

    @PostMapping("/book/{id}/edit-admin")
    public String bookEdit(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/home";
    }

    @GetMapping("/book/{id}/delete-admin")
    public String bookDelete(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        bookService.deleteBook(book);
        return "redirect:/home";
    }

    @GetMapping("/book/{id}/add-lib")
    public String bookAdd(@ModelAttribute Library library, @PathVariable Long id, Principal principal) {
        User user = userService.userByName(principal.getName());
        Book book = bookService.getBookById(id);
        if (libraryService.bookExist(book, user)) {
            Library lib = libraryService.getLibrary(book, user);
            lib.setUser(user);
            lib.setBook(book);
            lib.setStatus(statusService.findById(library.getStatus().getId()));
            libraryService.save(lib);

        } else {
            Library lib = new Library();
            lib.setUser(user);
            lib.setBook(book);
            lib.setStatus(statusService.findById(library.getStatus().getId()));
            libraryService.save(lib);
        }

        return "redirect:/book/" + id;
    }

    @GetMapping("/book/{id}/delete-lib")
    public String bookDelete(@PathVariable Long id, Principal principal) {
        User user = userService.userByName(principal.getName());
        Book book = bookService.getBookById(id);
        libraryService.deleteLibrary(book, user);
        return "redirect:/book/" + id;
    }

    @GetMapping("/home")
    public String getAllBook(Model model, Principal principal) {
        List<Book> books = bookService.getAllBook();
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("/profile")
    public String getUser(Model model, Principal principal) {
        User user = userService.userByName(principal.getName());
        List<Book> books1 = bookService.findAllByUserAndStatus(user.getId(),1L);
        List<Book> books2 = bookService.findAllByUserAndStatus(user.getId(),2L);
        List<Book> books3 = bookService.findAllByUserAndStatus(user.getId(),3L);
        List<Book> books4 = bookService.findAllByUserAndStatus(user.getId(),4L);
        List<Book> books5 = bookService.findAllByUserAndStatus(user.getId(),5L);
        List<Book> books6 = bookService.findAllByUserAndStatus(user.getId(),6L);
        model.addAttribute("books1", books1);
        model.addAttribute("books2", books2);
        model.addAttribute("books3", books3);
        model.addAttribute("books4", books4);
        model.addAttribute("books5", books5);
        model.addAttribute("books6", books6);
        return "profile";
    }

    @GetMapping("/add-book")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@RequestParam("file") MultipartFile file, Book book){
        String name = "src\\main\\resources\\static\\img\\";
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        System.out.println(file.getContentType());
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + book.getIsbn() + "." + extension)));
                stream.write(bytes);
                stream.close();
                book.setImg("/img/" + book.getIsbn() + "." + extension);
                bookService.addBook(book);
                return "redirect:/home";
            } catch (Exception e) {
                return "redirect:/error-upload";
            }
        } else {
            return "redirect:/error-upload";
        }
    }

    @RequestMapping("/error-upload")
    public String errorUpload() {
        return "error-upload";
    }

    @RequestMapping("/profile/search")
    public String search(@RequestParam("query") String query, Model model) {
        query = query.toLowerCase();
        List<Book> books = bookService.searchBook(query);
        model.addAttribute("books", books);
        return "search";
    }
}
