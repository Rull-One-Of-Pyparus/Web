package com.example.web.Repository;

import com.example.web.Config.Entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Book,Long> {


}
