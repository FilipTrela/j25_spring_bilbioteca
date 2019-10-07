package pl.sda.gdajava25.spring.bilbioteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.gdajava25.spring.bilbioteka.model.BookLent;

public interface BookLentRepository extends JpaRepository<BookLent,Long> {
}
