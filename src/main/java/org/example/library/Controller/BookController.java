package org.example.library.Controller;

import lombok.RequiredArgsConstructor;
import org.example.library.Service.*;
import org.example.library.Model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/library")
@CrossOrigin(origins="http://localhost:5173")
public class BookController {

    private final BookService bs;
    @GetMapping("/all")
    public List<Book> getBooks(){
        return bs.getAllBooks();
    }

    @PostMapping
    public void addBook(@RequestBody Book book){
        bs.addBook(book);
    }


}
