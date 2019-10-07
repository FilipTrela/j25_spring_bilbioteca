package pl.sda.gdajava25.spring.bilbioteka.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.gdajava25.spring.bilbioteka.model.Reader;
import pl.sda.gdajava25.spring.bilbioteka.repository.ReaderRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;

    public List<Reader> getAllReader() {
        return readerRepository.findAll();
    }

    public void add(Reader reader) {
        readerRepository.save(reader);
    }

    public Optional<Reader> findById(Long id) {
        return readerRepository.findById(id);
    }

    public void delete(Reader reader) {
        readerRepository.delete(reader);
    }

    public void deleteById(Long id) {
        readerRepository.deleteById(id);
    }

}
