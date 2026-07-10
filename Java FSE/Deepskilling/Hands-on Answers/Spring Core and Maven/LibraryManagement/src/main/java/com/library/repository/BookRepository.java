package com.library.repository;
import java.util.*; import org.springframework.stereotype.Repository;
@Repository public class BookRepository { private final Map<String,String> books=new HashMap<>(); public void save(String isbn,String title){books.put(isbn,title);} public Optional<String> find(String isbn){return Optional.ofNullable(books.get(isbn));} }
