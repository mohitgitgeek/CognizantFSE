package com.library.service;
import com.library.repository.BookRepository; import org.springframework.stereotype.Service;
@Service public class BookService { private BookRepository repository; public BookService(BookRepository repository){this.repository=repository;} public void setBookRepository(BookRepository repository){this.repository=repository;} public String addAndFind(String isbn,String title){repository.save(isbn,title);return repository.find(isbn).orElseThrow();} }
