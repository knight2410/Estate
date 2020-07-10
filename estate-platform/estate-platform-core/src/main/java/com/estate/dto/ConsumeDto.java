package com.estate.dto;

public class ConsumeDto extends AbstractDTO<ConsumeDto> {

    private Long idUnitPrice;
    private Long contractId;
    private Long idBuilding;
    private Long idCustomer;
    private Double waterNumber;
    private Double powerNumber;
    private Double percentDaysStay;
    private UnitPriceDto unitPriceDto;
    private String noteContract;
    private Double amountPaid;
    private Double amountPayable;
    private Double waterNumberNew;
    private Double powerNumberNew;
    private String note;

    private Double debt;

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(Double amountPayable) {
        this.amountPayable = amountPayable;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Double getWaterNumberNew() {
        return waterNumberNew;
    }

    public void setWaterNumberNew(Double waterNumberNew) {
        this.waterNumberNew = waterNumberNew;
    }

    public Double getPowerNumberNew() {
        return powerNumberNew;
    }

    public void setPowerNumberNew(Double powerNumberNew) {
        this.powerNumberNew = powerNumberNew;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getNoteContract() {
        return noteContract;
    }

    public void setNoteContract(String noteContract) {
        this.noteContract = noteContract;
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

    public UnitPriceDto getUnitPriceDto() {
        return unitPriceDto;
    }

    public void setUnitPriceDto(UnitPriceDto unitPriceDto) {
        this.unitPriceDto = unitPriceDto;
    }
}
