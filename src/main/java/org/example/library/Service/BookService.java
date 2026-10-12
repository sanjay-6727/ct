package org.example.library.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.example.library.Repository.*;
import org.example.library.Model.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository br;

    public void addBook(Book book){
        br.save(book);
    }

    public List<Book> getAllBooks(){
        return br.findAll();
    }

    public Optional<Book> getBookByid(String id){
        return br.findById(id);
    }

    public void deleteId(String id){
        br.deleteById(id);
    }

    public Optional<Book> updatebook(String id, Book newBook){
        return br.findById(id).map(book ->{
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setCopiesAvailable(newBook.getCopiesAvailable());

            return br.save(book);
        });
    }
}
