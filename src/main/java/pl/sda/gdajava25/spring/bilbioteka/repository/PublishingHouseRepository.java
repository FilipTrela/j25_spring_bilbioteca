package pl.sda.gdajava25.spring.bilbioteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.gdajava25.spring.bilbioteka.model.PublishingHouse;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouse,Long> {
}
