package com.estate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "consume")
public class ConsumeEntity extends BaseEntity {

    @Column
    private Long idContract;
    @Column
    private Long idUnitPrice;
    @Column
    private Long idBuilding;
    @Column
    private Double waterNumber;
    @Column
    private Double powerNumber;
    @Column
    private Double percentDaysStay;
    @Column(columnDefinition = "TEXT")
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(Long idBuilding) {
        this.idBuilding = idBuilding;
    }

    public Double getWaterNumber() {
        return waterNumber;
    }

    public void setWaterNumber(Double waterNumber) {
        this.waterNumber = waterNumber;
    }

    public Double getPowerNumber() {
        return powerNumber;
    }

    public void setPowerNumber(Double powerNumber) {
        this.powerNumber = powerNumber;
    }

    public Double getPercentDaysStay() {
        return percentDaysStay;
    }

    public void setPercentDaysStay(Double percentDaysStay) {
        this.percentDaysStay = percentDaysStay;
    }

    public Long getIdUnitPrice() {
        return idUnitPrice;
    }

    public void setIdUnitPrice(Long idUnitPrice) {
        this.idUnitPrice = idUnitPrice;
    }

    public Long getIdContract() {
        return idContract;
    }

    public void setIdContract(Long idContract) {
        this.idContract = idContract;
    }
}
