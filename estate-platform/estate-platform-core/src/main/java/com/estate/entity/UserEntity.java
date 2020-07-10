package com.estate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity
{
    private static final long serialVersionUID = -5426562322680764256L;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String fullName;

    @Column
    private  String email;

    @Column
    private  String phone;

    @Column
    private int status;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false) })
    private List<RoleEntity> roleList;


    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "staffs")
    private List<BuildingEntity> buildings;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "staffsPrioritize", cascade = {CascadeType.REFRESH})
    private List<BuildingEntity> buildingsPrioritize;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private List<CustomerEntity> customers;
    
    
    public List<CustomerEntity> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerEntity> customers) {
        this.customers = customers;
    }

    public List<BuildingEntity> getBuildingsPrioritize() {
        return buildingsPrioritize;
    }

    public void setBuildingsPrioritize(List<BuildingEntity> buildingsPrioritize) {
        this.buildingsPrioritize = buildingsPrioritize;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RoleEntity> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleEntity> roleList) {
        this.roleList = roleList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<BuildingEntity> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingEntity> buildings) {
        this.buildings = buildings;
    }


}
