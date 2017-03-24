package es.uniovi.asw.persistence;

import es.uniovi.asw.model.Citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    @Query("Select a from Citizen a where a.id=?1")
    Citizen findByID(long id);

    @Query("Select a from Citizen a where a.dni=?1")
    Citizen findById(String dni);
}
