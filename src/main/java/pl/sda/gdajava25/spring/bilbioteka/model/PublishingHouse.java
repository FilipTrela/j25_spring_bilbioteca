package pl.sda.gdajava25.spring.bilbioteka.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PublishingHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "publishingHouse",fetch = FetchType.EAGER)
    private Set<Book> books;

    public PublishingHouse(String name) {
        this.name = name;
    }
}
