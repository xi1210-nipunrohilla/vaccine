package com.VaccNow.vaccine.controller;

import com.VaccNow.vaccine.service.VaccNowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class VaccinationController {

    @Autowired
    private VaccNowService vaccNowService;

    private static Logger logger = LoggerFactory.getLogger(VaccinationController.class);

    @GetMapping("/scheduleTimeSlot/{branchId}/{dateTime}")
    public boolean bookVaccinationTimeSlot(@PathVariable("dateTime") String dateTime,
                                           @PathVariable("branchId") Integer branchId){
        logger.info("In controller, preparing to schedule time slot :[{}] for branch id:[{}]", dateTime, branchId);
        LocalDateTime parse = LocalDateTime.parse(dateTime);
        boolean status = vaccNowService.bookTimeSlotForVaccination(parse, branchId);
        if (status){
            logger.info("In controller, successfully booked time slot :[{}] for branch id:[{}]", dateTime, branchId);
        }else {
            logger.info("In controller, Cannot booked time slot :[{}] for branch id:[{}]", dateTime, branchId);
        }
        return status;
    }

    //Wont be able to implement payment method as it requires paypal sanbox developer access but I know the working
    //Also email functionality can be implemented using java mail api but I know the working


}
