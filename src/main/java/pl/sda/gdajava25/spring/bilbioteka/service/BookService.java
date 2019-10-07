package pl.sda.gdajava25.spring.bilbioteka.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.gdajava25.spring.bilbioteka.model.Book;
import pl.sda.gdajava25.spring.bilbioteka.model.PublishingHouse;
import pl.sda.gdajava25.spring.bilbioteka.repository.BookRepository;
import pl.sda.gdajava25.spring.bilbioteka.repository.PublishingHouseRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PublishingHouseRepository publishingHouse;


    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public void add(Book book, Long phId){
        book.setPublishingHouse(publishingHouse.getOne(phId));
        bookRepository.save(book);
    }
    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }
    public void delete(Book book){
        bookRepository.delete(book);
    }
    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }
}
