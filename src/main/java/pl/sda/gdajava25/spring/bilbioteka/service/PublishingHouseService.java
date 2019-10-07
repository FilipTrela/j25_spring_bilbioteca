package pl.sda.gdajava25.spring.bilbioteka.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.gdajava25.spring.bilbioteka.model.PublishingHouse;
import pl.sda.gdajava25.spring.bilbioteka.repository.PublishingHouseRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;

    public List<PublishingHouse> getAllPublishingHouse(){
        return publishingHouseRepository.findAll();
    }
    public void add(PublishingHouse publishingHouse){
        publishingHouseRepository.save(publishingHouse);
    }
    public Optional<PublishingHouse> findById(Long id){
        return publishingHouseRepository.findById(id);
    }
    public void delete(PublishingHouse publishingHouse){
        publishingHouseRepository.delete(publishingHouse);
    }
    public void deleteById(Long id){
        publishingHouseRepository.deleteById(id);
    }
}
