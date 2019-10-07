package pl.sda.gdajava25.spring.bilbioteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.gdajava25.spring.bilbioteka.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
