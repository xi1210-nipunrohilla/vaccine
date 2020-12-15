package com.VaccNow.vaccine.controller;

import com.VaccNow.vaccine.model.Branch;
import com.VaccNow.vaccine.model.BranchSchedule;
import com.VaccNow.vaccine.model.Vaccine;
import com.VaccNow.vaccine.service.VaccNowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class AvailabilityController {
    private static Logger logger = LoggerFactory.getLogger(AvailabilityController.class);

    @Autowired
    private VaccNowService vaccNowService;

    /*Return all the available branches
    * */
    @GetMapping("/allBranch")
    public List<Branch> getBranch(){
        logger.info("In controller, Preparing to fetch list of branches..");
        List<Branch> branches = vaccNowService.fetchAllBranches();
        logger.info("In controller, successfully fetched list of branches");
        return branches;
    }

    /* Return all the available vaccines wrt branch
    * */
    @GetMapping("/availableVaccines")
    public List<Vaccine> getVaccinesPerBranch(){
        logger.info("In controller, preparing to fetch all available vaccines with branch");
        List<Vaccine> vaccines = vaccNowService.fetchAllAvailableVaccines();
        logger.info("In controller, successfully fetched all available vaccines with");
        return vaccines;
    }

    @GetMapping("/getSpecificScheduleByBranch/{branchId}/{dateTime}")
    public BranchSchedule getSpecificScheduleByBranch(@PathVariable(value = "branchId", required = true)  Integer branchId,
                                                            @PathVariable(value = "dateTime") String dateTime){
        logger.info("In controller, preparing to fetch the specific vaccine schedule by branchId.");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        BranchSchedule branchSchedule = vaccNowService.fetchSpecificScheduleByBranch(branchId, localDateTime);
        logger.info("In controller, successfully fetched the specific vaccines schedule by branchId.");
        return branchSchedule;
    }

    @GetMapping("/getAvailableTimeByBranch/{branchId}")
    public List<BranchSchedule> fetchAvailableTimeForBranch(@PathVariable(value = "branchId") Integer branchId){
        logger.info("In controller, preparing to fetch all the available time slot for branchID:[{}]", branchId);
        List<BranchSchedule> branchSchedules = vaccNowService.fetchAvailableTimeForBranch(branchId);
        logger.info("In controller, Successfully fetched all the available time slots for branchId:[{}]", branchId);
        return branchSchedules;

    }
}