package com.example.web.Services;

import com.example.web.Config.Entity.Book;
import com.example.web.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book getBookById(Long id) { return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);}

}
