package com.VaccNow.vaccine.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vaccine_tb")
public class Vaccine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vaccine_batch_id")
    private Integer vaccineBatchId;

    @Column(name = "vaccine_branch_id")
    private Integer vaccineBranchId;

    @Column(name = "quantity")
    private long quantity;

    public Vaccine() {
        super();
    }

    public Integer getVaccineBatchId() {
        return vaccineBatchId;
    }

    public void setVaccineBatchId(Integer vaccineBatchId) {
        this.vaccineBatchId = vaccineBatchId;
    }

    public Integer getVaccineBranchId() {
        return vaccineBranchId;
    }

    public void setVaccineBranchId(Integer vaccineBranchId) {
        this.vaccineBranchId = vaccineBranchId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
