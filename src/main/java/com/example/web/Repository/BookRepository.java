package com.example.web.Repository;

import com.example.web.Config.Entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,Long> {

    List<Book> findAll();

    @Query(value = "SELECT * from books b join library l on b.id = l.id where l.user_id = :userId AND l.status_id = :statusId", nativeQuery = true)
    List<Book> findAllByUserAndStatus(Long userId, Long statusId);
}
