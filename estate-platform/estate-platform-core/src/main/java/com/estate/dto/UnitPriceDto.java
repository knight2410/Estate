package com.estate.dto;

import java.util.Date;

public class UnitPriceDto extends AbstractDTO<UnitPriceDto> {

    private Long idBuilding;
    private Double waterPrice;
    private Double electricityPrice;
    private Double roomPrice;
    private Date effectiveDate;
    private String noteUnitPrice;

    public String getNoteUnitPrice() {
        return noteUnitPrice;
    }

    public void setNoteUnitPrice(String noteUnitPrice) {
        this.noteUnitPrice = noteUnitPrice;
    }

    public Long getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(Long idBuilding) {
        this.idBuilding = idBuilding;
    }

    public Double getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(Double waterPrice) {
        this.waterPrice = waterPrice;
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

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
