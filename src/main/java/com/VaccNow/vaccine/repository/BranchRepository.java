package com.VaccNow.vaccine.repository;

import com.VaccNow.vaccine.model.Branch;
import com.VaccNow.vaccine.model.BranchSchedule;
import com.VaccNow.vaccine.model.Vaccine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Integer> {

    @Query(value = "select * from branch_tb", nativeQuery = true)
    List<Branch> findAllBranches();



}