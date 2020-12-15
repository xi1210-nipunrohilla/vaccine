package com.VaccNow.vaccine.repository;

import com.VaccNow.vaccine.model.Vaccine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends CrudRepository<Vaccine, Integer> {

    @Query(value = "select * from vaccine_tb", nativeQuery = true)
    List<Vaccine> findAllAvailableVaccines();
}
