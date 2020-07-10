package com.estate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class ContractCustomEntity {

    @Column
    private String nameBuilding;
    @Column
    private String nameCustomer;
    @Column
    private Date createdDate;
    @Column
    private Date createdDateConsumer;
    @Column
    private Long idConsumer;
    @Column
    private Long idPayment;
    @Column
    private Double amountPaid;

    public Date getCreatedDateConsumer() {
        return createdDateConsumer;
    }

    public void setCreatedDateConsumer(Date createdDateConsumer) {
        this.createdDateConsumer = createdDateConsumer;
    }

    public Long getIdConsumer() {
        return idConsumer;
    }

    public void setIdConsumer(Long idConsumer) {
        this.idConsumer = idConsumer;
    }

    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
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
