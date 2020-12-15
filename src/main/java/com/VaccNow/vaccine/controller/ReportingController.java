package com.VaccNow.vaccine.controller;

import com.VaccNow.vaccine.model.BranchSchedule;
import com.VaccNow.vaccine.service.VaccNowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ReportingController {
    private static Logger logger = LoggerFactory.getLogger(ReportingController.class);

    @Autowired
    private VaccNowService vaccNowService;

    @GetMapping("/allAppliedVaccinationByBranch/{branchId}")
    public List<BranchSchedule> getAllAppliedVaccinationByBranch(@PathVariable("branchId")Integer branchId){
        logger.info("In Reporting controller, fetching all applied vaccination details by branch:[{}]", branchId);
        List<BranchSchedule> appliedVaccinesByBranch = vaccNowService.fetchAllAppliedVaccinesByBranch(branchId);
        logger.info("In Reporting controller, Successfully fetched all applied vaccines details by branch:[{}]", branchId);
        return appliedVaccinesByBranch;
    }

    @GetMapping("/allAppliedVaccinationPerDay/{dateTime}")
    public List<BranchSchedule> getAllAppliedVaccinationPerDay(@PathVariable("dateTime") String dateTime){
        logger.info("In Reporting controller, Fetching all applied vaccines for day:[{}]", dateTime);
        LocalDateTime parse = LocalDateTime.parse(dateTime);
        List<BranchSchedule> fetchAllAppliedVaccinesByDay = vaccNowService.fetchAllAppliedVaccinesByDay(parse);
        logger.info("In Reporting controller, Successfully fetched all applied vaccines for day:[{}]", dateTime);
        return fetchAllAppliedVaccinesByDay;
    }

    @GetMapping("/allConfirmedVaccinationOverPeriod/{fromDateTime}/{toDateTime}")
    public List<BranchSchedule> getAllConfirmedVaccinationOverPeriod(@PathVariable("fromDateTime")String fromDateTime,
                                                                     @PathVariable("toDateTime") String toDateTime){
        logger.info("In Reporting controller, Fetching all confirmed vaccination over time period");
        LocalDateTime from = LocalDateTime.parse(fromDateTime);
        LocalDateTime to = LocalDateTime.parse(toDateTime);
        List<BranchSchedule> branchConfirmedSchedules = vaccNowService.fetchAllConfirmedVaccinationOverPeriod(from, to);
        logger.info("In Reporting controller, Successfully fetched all confirmed Vaccination over time period");
        return branchConfirmedSchedules;
    }
}
