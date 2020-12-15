package com.VaccNow.vaccine.repository;

import com.VaccNow.vaccine.model.BranchSchedule;
import com.VaccNow.vaccine.model.BranchScheduleIdentity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BranchScheduleRepository extends CrudRepository<BranchSchedule, BranchScheduleIdentity> {

    @Query(value = "select * from branch_schedule_tb b where b.branch_id = :branchId and b.time_date = :dateTime and b.status =:open ", nativeQuery = true)
    BranchSchedule fetchSpecificAvailableScheduleByBranchAndTime(Integer branchId, LocalDateTime dateTime, String open);

    @Query(value = "select * from branch_schedule_tb b where b.branch_id = :branchId and b.status =:open", nativeQuery = true)
    List<BranchSchedule> fetchAllAvailableTimeSlotsForGivenBranch(Integer branchId, String open);

    @Query(value = "select * from branch_schedule_tb b where b.branch_id= :branchId and b.status =:used", nativeQuery = true)
    List<BranchSchedule> fetchAllAppliedVaccinesByBranch(Integer branchId, String used);

    @Query(value = "select * from branch_schedule_tb b where b.applied_on = :dateTime and b.status =:used", nativeQuery = true)
    List<BranchSchedule> fetchAllAppliedVaccinesByDay(LocalDateTime dateTime, String used);

    @Query(value = "select * from branch_schedule_tb b where b.created_on BETWEEN :from AND :to AND b.status IN (:used, :reserved)", nativeQuery = true)
    List<BranchSchedule> fetchAllConfirmedVaccinesOverPeriod(LocalDateTime from, LocalDateTime to, String used, String reserved);
}
