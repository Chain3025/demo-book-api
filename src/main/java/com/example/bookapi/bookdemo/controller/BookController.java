package com.example.bookapi.bookdemo.controller;

import com.example.bookapi.bookdemo.model.Book;
import com.example.bookapi.bookdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Object> createBook(@RequestBody Book book){
        bookService.addNewBook(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(book.getBookId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public void updateBook(@RequestBody Book book,
                           @RequestParam(value =  "bookid")Long id){
        bookService.updateBookData(book,id);

    }

    @GetMapping()
    public List<Book> getAllBook(){

        return bookService.getAllBooks();
    }

    @GetMapping(path = "/id/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @GetMapping(path = "/title/{title}")
    public List<Book> getBookByTitle(@PathVariable String title){
        return bookService.getBookByTitle(title);
    }

    @DeleteMapping(path = "/id")
    public void deleteBookById(@RequestParam(value ="bookid") Long id){
        bookService.deleteBookById(id);
    }

    @DeleteMapping
    public void deleteBook(@RequestBody Book book){
        bookService.deleteBook(book);
    }


}
