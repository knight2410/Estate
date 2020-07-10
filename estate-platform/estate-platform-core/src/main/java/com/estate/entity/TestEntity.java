package com.estate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class TestEntity  implements Serializable {
    private BigInteger id;
    private String nameBuilding;
    private String nameCustomer;
    private Date createdDate;
    private Date createdDateConsumer;
    private BigInteger idConsumer;
    private BigInteger idPayment;
    private Double amountPaid;
    private Double powerNumber;
    private Double waterNumber;
    private Double electricityPrice;
    private Double roomPrice;
    private Double waterPrice;
    private Date effectiveDate;
    private Double amountPayable;
    private Double percentDaysStay;
    private String createByContract;

    public String getCreateByContract() {
        return createByContract;
    }

    public void setCreateByContract(String createByContract) {
        this.createByContract = createByContract;
    }

    public Double getPowerNumber() {
        return powerNumber;
    }

    public void setPowerNumber(Double powerNumber) {
        this.powerNumber = powerNumber;
    }

    public Double getWaterNumber() {
        return waterNumber;
    }

    public void setWaterNumber(Double waterNumber) {
        this.waterNumber = waterNumber;
    }

    public Double getElectricityPrice() {
        return electricityPrice;
    }

    public void setElectricityPrice(Double electricityPrice) {
        this.electricityPrice = electricityPrice;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Double getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(Double waterPrice) {
        this.waterPrice = waterPrice;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Double getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(Double amountPayable) {
        this.amountPayable = amountPayable;
    }

    public Double getPercentDaysStay() {
        return percentDaysStay;
    }

    public void setPercentDaysStay(Double percentDaysStay) {
        this.percentDaysStay = percentDaysStay;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getCreatedDateConsumer() {
        return createdDateConsumer;
    }

    public void setCreatedDateConsumer(Date createdDateConsumer) {
        this.createdDateConsumer = createdDateConsumer;
    }

    public BigInteger getIdConsumer() {
        return idConsumer;
    }

    public void setIdConsumer(BigInteger idConsumer) {
        this.idConsumer = idConsumer;
    }

    public BigInteger getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(BigInteger idPayment) {
        this.idPayment = idPayment;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getNameBuilding() {
        return nameBuilding;
    }

    public void setNameBuilding(String nameBuilding) {
        this.nameBuilding = nameBuilding;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
