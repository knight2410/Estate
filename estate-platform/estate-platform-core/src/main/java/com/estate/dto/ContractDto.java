package com.estate.dto;

import java.util.Date;
import java.util.Map;

public class ContractDto extends AbstractDTO<ContractDto> {

    private Long idBuilding;

    private Long idCustomer;

    private Long idUser;

    private Date isDelete;

    private String nameBuilding;

    private String nameCustomer;

    private String nameUser;

    private String note;

    private String startDate;

    private String endDate;

    private String startDateUnitprice;

    private String endDateUnitPrice;

    private Integer deleteBuilding = 1;

    private Integer processed1;

    private Integer paidMoney;

    private boolean active = false;

    private Date createdDateConsumer;

    private Double amountPaid;

    private int monthCreatedDateConsumer;
    public Long getIdBuilding() {
        return idBuilding;
    }

    private Double powerNumber;
    private Double waterNumber;
    private Double electricityPrice;
    private Double roomPrice;
    private Double waterPrice;
    private Date effectiveDate;
    private Double amountPayable;
    private Double percentDaysStay;

    private String startDateConsume;
    private String endDateConsume;

    public String getStartDateConsume() {
        return startDateConsume;
    }

    public void setStartDateConsume(String startDateConsume) {
        this.startDateConsume = startDateConsume;
    }

    public String getEndDateConsume() {
        return endDateConsume;
    }

    public void setEndDateConsume(String endDateConsume) {
        this.endDateConsume = endDateConsume;
    }

    public String getStartDateUnitprice() {
        return startDateUnitprice;
    }

    public void setStartDateUnitprice(String startDateUnitprice) {
        this.startDateUnitprice = startDateUnitprice;
    }

    public String getEndDateUnitPrice() {
        return endDateUnitPrice;
    }

    public void setEndDateUnitPrice(String endDateUnitPrice) {
        this.endDateUnitPrice = endDateUnitPrice;
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

    public void setIdBuilding(Long idBuilding) {
        this.idBuilding = idBuilding;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Date getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Date isDelete) {
        this.isDelete = isDelete;
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

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getDeleteBuilding() {
        return deleteBuilding;
    }

    public void setDeleteBuilding(Integer deleteBuilding) {
        this.deleteBuilding = deleteBuilding;
    }

    public Integer getProcessed1() {
        return processed1;
    }

    public void setProcessed1(Integer processed1) {
        this.processed1 = processed1;
    }

    public Integer getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(Integer paidMoney) {
        this.paidMoney = paidMoney;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedDateConsumer() {
        return createdDateConsumer;
    }

    public void setCreatedDateConsumer(Date createdDateConsumer) {
        this.createdDateConsumer = createdDateConsumer;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getMonthCreatedDateConsumer() {
        return monthCreatedDateConsumer;
    }

    public void setMonthCreatedDateConsumer(int monthCreatedDateConsumer) {
        this.monthCreatedDateConsumer = monthCreatedDateConsumer;
    }
}
