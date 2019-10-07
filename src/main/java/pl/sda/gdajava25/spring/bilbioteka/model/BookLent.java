package pl.sda.gdajava25.spring.bilbioteka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BookLent{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ToString.Exclude
    @ManyToOne
    private Reader client;

    @ToString.Exclude
    @ManyToOne
    private Book book;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dateLent;

    private LocalDateTime dateReturned;

}