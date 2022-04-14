package com.example.web.Services;

import com.example.web.Config.Entity.Book;
import com.example.web.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book getBookById(Long id) { return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new); }

    public List<Book> getAllBook() { return bookRepository.findAll(); }

    public List<Book> findAllByUserAndStatus(Long userId, Long statusId) { return bookRepository.findAllByUserAndStatus(userId, statusId); }

    public void addBook(Book book) { bookRepository.save(book); }

    public void deleteBook(Book book) { bookRepository.deleteById(book.getId()); }

    public List<Book> searchBook(String title) {
        title = title.toLowerCase();
        return bookRepository.searchBook(title);
    }

}
