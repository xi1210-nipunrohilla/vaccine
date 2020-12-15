package com.VaccNow.vaccine.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class BranchScheduleIdentity implements Serializable {

    @Column(name = "branch_id")
    private Integer branchId;

    @Column(name = "time_date")
    private LocalDateTime timeDate;

    public BranchScheduleIdentity() {
        super();
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public LocalDateTime getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(LocalDateTime timeDate) {
        this.timeDate = timeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchScheduleIdentity that = (BranchScheduleIdentity) o;
        return Objects.equals(branchId, that.branchId) &&
                Objects.equals(timeDate, that.timeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, timeDate);
    }
}
