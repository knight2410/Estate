package com.estate.dto;

import java.io.Serializable;
import java.util.Date;

public class DetailContract extends AbstractDTO<DetailContract> {

    private String createByContract;
    private String nameBuilding;
    private String nameCustomer;
    private Date createDateConsume;
    private Double powerNumber;
    private Double waterNumber;
    private Double electricityPrice;
    private Double roomPrice;
    private Double waterPrice;
    private Double amountPaid;
    private Double amountPayable;
    private Double percentDaysStay;
    private String startDateConsume;
    private String endDateConsume;
    private Long idContract;
    private Long idCustomer;
    private Long idBuilding;
    private Long idUser;
    private Date isDelete;
    private String nameUser;

    private String startContract;
    private String endContract;

    private String startDelete;
    private String endDelete;


    public String getStartContract() {
        return startContract;
    }

    public void setStartContract(String startContract) {
        this.startContract = startContract;
    }

    public String getEndContract() {
        return endContract;
    }

    public void setEndContract(String endContract) {
        this.endContract = endContract;
    }

    public String getStartDelete() {
        return startDelete;
    }

    public void setStartDelete(String startDelete) {
        this.startDelete = startDelete;
    }

    public String getEndDelete() {
        return endDelete;
    }

    public void setEndDelete(String endDelete) {
        this.endDelete = endDelete;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(Long idBuilding) {
        this.idBuilding = idBuilding;
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

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Long getIdContract() {
        return idContract;
    }

    public void setIdContract(Long idContract) {
        this.idContract = idContract;
    }

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

    public Double getPercentDaysStay() {
        return percentDaysStay;
    }

    public void setPercentDaysStay(Double percentDaysStay) {
        this.percentDaysStay = percentDaysStay;
    }

    public String getCreateByContract() {
        return createByContract;
    }

    public void setCreateByContract(String createByContract) {
        this.createByContract = createByContract;
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

    public Date getCreateDateConsume() {
        return createDateConsume;
    }

    public void setCreateDateConsume(Date createDateConsume) {
        this.createDateConsume = createDateConsume;
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

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Double getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(Double amountPayable) {
        this.amountPayable = amountPayable;
    }
}
