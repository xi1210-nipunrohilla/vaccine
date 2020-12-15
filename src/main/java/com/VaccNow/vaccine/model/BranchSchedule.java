package com.VaccNow.vaccine.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "branch_schedule_tb")
public class BranchSchedule implements Serializable {

    @EmbeddedId
    private BranchScheduleIdentity branchScheduleIdentity;

    @Column(name = "schedule_patient_id")
    private Long schedulePatientId;

    @Column(name = "status")
    private String status;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "applied_on")
    private LocalDateTime appliedOn;

    public BranchSchedule() {
        super();
    }

    public BranchScheduleIdentity getBranchScheduleIdentity() {
        return branchScheduleIdentity;
    }

    public void setBranchScheduleIdentity(BranchScheduleIdentity branchScheduleIdentity) {
        this.branchScheduleIdentity = branchScheduleIdentity;
    }

    public Long getSchedulePatientId() {
        return schedulePatientId;
    }

    public void setSchedulePatientId(Long schedulePatientId) {
        this.schedulePatientId = schedulePatientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(LocalDateTime appliedOn) {
        this.appliedOn = appliedOn;
    }
}
