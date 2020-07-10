package com.estate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "district")
public class DistrictEntity extends BaseEntity {

    private static final long serialVersionUID = -7758709700975343037L;

    @Column
    private String name;

    @Column(unique = true)
    private  String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
