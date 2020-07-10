package com.estate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class PaymentEntity extends BaseEntity {

    @Column
    private Long idConsume;
//    @Column
//    private Long idContract;
    @Column
    private Double amountPayable;
    @Column
    private Double amountPaid;

//    public Long getIdContract() {
//        return idContract;
//    }
//
//    public void setIdContract(Long idContract) {
//        this.idContract = idContract;
//    }

    public Double getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(Double amountPayable) {
        this.amountPayable = amountPayable;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Long getIdConsume() {
        return idConsume;
    }

    public void setIdConsume(Long idConsume) {
        this.idConsume = idConsume;
    }
}
