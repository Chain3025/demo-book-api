package com.example.bookapi.bookdemo.service;

import com.example.bookapi.bookdemo.model.Book;
import com.example.bookapi.bookdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    
    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBookData(Book bookRequest, Long id) {

        Optional<Book> bookOptional = bookRepository.findById(id);
        if(!bookOptional.isPresent()) {
            return;
        }
        Book book = bookOptional.get();
        if(bookRequest.getBookName() != null){
            book.setBookName(bookRequest.getBookName()); ;
        }
        if(bookRequest.getBookAuthor() != null){
            book.setBookAuthor(bookRequest.getBookAuthor()); ;
        }
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent()) return null;
        return book.get();
    }

    public List<Book> getBookByTitle(String title) {
        return bookRepository.findByBookNameEqualsIgnoreCase(title);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
