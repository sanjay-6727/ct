package org.example.library.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="Books")

public class Book {
    @Id
    private String  id;
    private String title;
    private String author;
    private int copiesAvailable;

}
