
package org.example.library.Service;

import lombok.RequiredArgsConstructor;
import org.example.library.Model.Book;
import org.example.library.Repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository br;

    // ==================== CREATE ====================

    public Book addBook(Book book) {
        return br.save(book);
    }

    // ==================== READ ====================

    // Get all books
    public List<Book> getAllBooks() {
        return br.findAll();
    }

    // Get book by ID
    public Optional<Book> getBookById(String id) {
        return br.findById(id);
    }

    // Search by exact author
    public List<Book> getBooksByAuthor(String author) {
        return br.findByAuthor(author);
    }

    // Search titles containing a keyword
    public List<Book> searchBooksByTitle(String title) {
        return br.findByTitleContainingIgnoreCase(title);
    }

    // Find books with more than a given number of copies
    public List<Book> getBooksWithCopiesGreaterThan(int copies) {
        return br.findByCopiesAvailableGreaterThan(copies);
    }

    // Find books with fewer than a given number of copies
    public List<Book> getBooksWithCopiesLessThan(int copies) {
        return br.findByCopiesAvailableLessThan(copies);
    }

    // Find books by author and title keyword
    public List<Book> searchByAuthorAndTitle(
            String author, String title) {
        return br.findByAuthorAndTitleContainingIgnoreCase(
                author, title);
    }

    // Sort alphabetically by title
    public List<Book> getBooksSortedByTitle() {
        return br.findAllByOrderByTitleAsc();
    }

    // Sort by available copies, highest first
    public List<Book> getBooksSortedByCopies() {
        return br.findAllByOrderByCopiesAvailableDesc();
    }

    // Get a page of books
    public Page<Book> getBooksPaginated(Pageable pageable) {
        return br.findAll(pageable);
    }

    // ==================== UPDATE ====================

    // Update an existing book
    public Optional<Book> updateBook(String id, Book newBook) {
        return br.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setCopiesAvailable(
                    newBook.getCopiesAvailable());

            return br.save(book);
        });
    }

    // Update only the number of available copies
    public Optional<Book> updateCopies(
            String id, int copiesAvailable) {

        return br.findById(id).map(book -> {
            book.setCopiesAvailable(copiesAvailable);
            return br.save(book);
        });
    }

    // ==================== DELETE ====================

    // Delete a book by ID
    public boolean deleteBook(String id) {
        if (!br.existsById(id)) {
            return false;
        }

        br.deleteById(id);
        return true;
    }

    // Delete all books
    public void deleteAllBooks() {
        br.deleteAll();
    }

    // Delete every book by a particular author
    public long deleteBooksByAuthor(String author) {
        return br.deleteByAuthorIgnoreCase(author);
    }

    // ==================== AGGREGATE / CHECKS ====================

    // Count all books
    public long countBooks() {
        return br.count();
    }

    // Count books by author
    public long countBooksByAuthor(String author) {
        return br.countByAuthorIgnoreCase(author);
    }

    // Check whether an ID exists
    public boolean bookExists(String id) {
        return br.existsById(id);
    }

    // Check whether a title exists
    public boolean titleExists(String title) {
        return br.existsByTitleIgnoreCase(title);
    }
}
