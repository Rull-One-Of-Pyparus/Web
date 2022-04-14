package com.example.web.Services;

import com.example.web.Config.Entity.Book;
import com.example.web.Config.Entity.Library;
import com.example.web.Config.Entity.User;
import com.example.web.Repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public void save(Library library){
        libraryRepository.save(library);
    }

    public boolean bookExist(Book book, User user) {
        return libraryRepository.findLibraryByBookAndUser(book, user)!=null;
    }

    public Library getLibrary(Book book, User user) {
        return libraryRepository.findLibraryByBookAndUser(book, user);
    }

    public void deleteLibrary(Book book, User user) {
        libraryRepository.deleteLibraryByBookAndUser(book, user);
    }
}
