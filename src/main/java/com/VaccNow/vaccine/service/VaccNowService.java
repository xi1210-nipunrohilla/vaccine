package com.VaccNow.vaccine.service;

import com.VaccNow.vaccine.model.Branch;
import com.VaccNow.vaccine.model.BranchSchedule;
import com.VaccNow.vaccine.model.Vaccine;
import com.VaccNow.vaccine.repository.BranchRepository;
import com.VaccNow.vaccine.repository.BranchScheduleRepository;
import com.VaccNow.vaccine.repository.VaccineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VaccNowService {
    private static Logger logger = LoggerFactory.getLogger(VaccNowService.class);

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private BranchScheduleRepository branchScheduleRepository;

    public List<Branch> fetchAllBranches() {
        logger.info("In service, preparing to fetch all the branches.");
        List<Branch> allBranches = branchRepository.findAllBranches();
        logger.info("Successfully fetched all the branches with total size:[{}]", allBranches.size());
        return allBranches;

    }

    public List<Vaccine> fetchAllAvailableVaccines() {
        logger.info("In service, preparing to fetch all available vaccines.");
        List<Vaccine> availableVaccines = vaccineRepository.findAllAvailableVaccines();
        logger.info("In service, successfully fetched list of all available vaccines per branch.");
        return availableVaccines;
    }

    public BranchSchedule fetchSpecificScheduleByBranch(Integer branchId, LocalDateTime dateTime) {
        logger.info("In service, preparing to fetch all the available schedule by branch from repository.");
        BranchSchedule branchSchedule = branchScheduleRepository.fetchSpecificAvailableScheduleByBranchAndTime(branchId, dateTime, "OPEN");
        logger.info("In service, successfully fetched all the available schedule by branch from repository");
        return branchSchedule;
    }

    public List<BranchSchedule> fetchAvailableTimeForBranch(Integer branchId){
        logger.info("In service, Preparing to fetch all the available time slots for given branch:[{}]", branchId);
        List<BranchSchedule> branchSchedules = branchScheduleRepository.fetchAllAvailableTimeSlotsForGivenBranch(branchId, "OPEN");
        logger.info("In service, Successfully fetched  all the available time slots for given branch:[{}]", branchId);
        return branchSchedules;
    }

    public boolean bookTimeSlotForVaccination(LocalDateTime parse, Integer branchId) {
        logger.info("In service, preparing to book time slot: [{}] of branchID:[{}] for vaccination", parse, branchId);
        if(parse.isBefore(LocalDateTime.now())){
            logger.info("Cannot book time slot for time period:[{}] as it is before current timestamp:[{}]", parse, LocalDateTime.now());
            return false;
        }

        //Check given time slot is available or not
        BranchSchedule openTimeSlot = branchScheduleRepository.fetchSpecificAvailableScheduleByBranchAndTime(branchId, parse, "OPEN");
        if (openTimeSlot != null){
            logger.info("Time slot:[{}] for branchId:[{}] is available", parse, branchId);
            //Setting the status to RESERVED and timestamp
            openTimeSlot.setStatus("RESERVED");
            openTimeSlot.setCreatedOn(LocalDateTime.now());
            branchScheduleRepository.save(openTimeSlot);
            return true;
        }else {
            logger.info("Time slot:[{}] is not available for branchId:[{}]", parse, branchId);
            return false;
        }
    }

    public List<BranchSchedule> fetchAllAppliedVaccinesByBranch(Integer branchId) {
        logger.info("In Service, Fetching all applied vaccines by branch:[{}]", branchId);
        List<BranchSchedule> appliedVaccinesByBranchList = branchScheduleRepository.fetchAllAppliedVaccinesByBranch(branchId, "USED");
        logger.info("In service, Successfully fetched applied vaccines by branch:[{}]", branchId);
        return appliedVaccinesByBranchList;
    }

    public List<BranchSchedule> fetchAllAppliedVaccinesByDay(LocalDateTime parse) {
        logger.info("In Service, Fetching all applied vaccines per day:[{}]", parse);
        List<BranchSchedule> fetchAllAppliedVaccinesByDay = branchScheduleRepository.fetchAllAppliedVaccinesByDay(parse, "USED");
        logger.info("In service, Successfully fetched all applied vaccines per day:[{}]", parse);
        return fetchAllAppliedVaccinesByDay;
    }

    public List<BranchSchedule> fetchAllConfirmedVaccinationOverPeriod(LocalDateTime from, LocalDateTime to){
        logger.info("In Service, Fetching all confirmed vaccines over period from:[{}] and to:[{}]", from, to);
        List<BranchSchedule> fetchAllAppliedVaccinesByDay = branchScheduleRepository.fetchAllConfirmedVaccinesOverPeriod(from, to, "USED", "RESERVED");
        logger.info("In Service, Fetching all confirmed vaccines over period from:[{}] and to:[{}]", from, to);
        return fetchAllAppliedVaccinesByDay;
    }
}