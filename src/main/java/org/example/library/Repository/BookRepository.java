package org.example.library.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import org.example.library.Model.*;

@Repository

public interface BookRepository extends MongoRepository<Book, String> {
}
