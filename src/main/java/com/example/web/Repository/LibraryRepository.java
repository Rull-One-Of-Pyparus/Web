package com.example.web.Repository;

import com.example.web.Config.Entity.Book;
import com.example.web.Config.Entity.Library;
import com.example.web.Config.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LibraryRepository extends CrudRepository<Library,Long> {

    @Transactional
    void deleteLibraryByBookAndUser(Book book, User user);

    Library findLibraryByBookAndUser(Book book, User user);
}
