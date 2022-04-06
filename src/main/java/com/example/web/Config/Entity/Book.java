package com.example.web.Config.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String name;
    private String author;
    private int year;
    private String language;
    private int page;
    private String genre;
    private String img;
    private String description;
}