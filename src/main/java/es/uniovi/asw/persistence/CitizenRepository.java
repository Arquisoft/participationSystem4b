package es.uniovi.asw.persistence;

import es.uniovi.asw.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

}
