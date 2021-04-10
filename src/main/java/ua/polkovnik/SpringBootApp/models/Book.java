package ua.polkovnik.SpringBootApp.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private String isbn;
    @NotNull
    private String title;
    @NotNull
    private String author;
}
