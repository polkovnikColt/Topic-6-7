package ua.polkovnik.SpringBootApp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
//@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonUser {
    @Id
    private String username;
    @NotNull
    private String password;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "book",
//            joinColumns = {@JoinColumn(name = "username")},
//            inverseJoinColumns = {@JoinColumn(name = "isbn")})
//    private List<Book> likedBooks;
}