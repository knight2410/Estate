package com.estate.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer_service")
public class CustomerServiceEntity extends BaseEntity{
    private static final long serialVersionUID = -7507397014679549178L;

    @Column
    private String date;

    @Column (columnDefinition = "TEXT")
    private String content;


    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
