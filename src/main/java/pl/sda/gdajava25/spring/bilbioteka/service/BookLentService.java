package pl.sda.gdajava25.spring.bilbioteka.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.gdajava25.spring.bilbioteka.model.BookLent;
import pl.sda.gdajava25.spring.bilbioteka.repository.BookLentRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BookLentService {
    private final BookLentRepository bookLentRepository;
    private final ReaderService readerService;
    private final BookService bookService;

    public void add(BookLent bookLent, Long idReader, Long bookId) {
        bookLent.setClient(readerService.findById(idReader).get());
        bookLent.setBook(bookService.findById(bookId).get());
        bookLentRepository.save(bookLent);
    }

    public void save(BookLent bookLent) {
        bookLentRepository.save(bookLent);
    }

    public Optional<BookLent> findById(Long id) {
        return bookLentRepository.findById(id);
    }


    public void deleteById(Long id) {
        bookLentRepository.deleteById(id);
    }
}
