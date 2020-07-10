package com.estate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
	private static final long serialVersionUID = -2472658315407497223L;
	@Column
	private String name;
	@Column
	private String ward;
	@Column
	private String street;
	@Column
	private String structure;
	@Column
	private Integer floorArea;
	@Column
	private String direction;
	@Column
	private String type;
	@Column
	private Integer rentArea;
	@Column
	private Integer price;
	@Column
	private String serviceFee;
	@Column
	private String motorParkingCharge;
	@Column
	private String carParkingCharge;
	@Column
	private String extraCharge;
	@Column
	private String electricCharge;
	@Column
	private String deposit;
	@Column
	private String payment;
	@Column
	private String timeForRent;
	@Column
	private String timeForDecorate;
	@Column
	private String managerName;
	@Column
	private String managerPhone;
	@Column
	private String agencyCharge;

	@Column(columnDefinition = "TEXT")
	private String types;

	@Column(columnDefinition = "TEXT")
	private String description;
	@Column
	private String link;
	@Column
	private String map;
	@Column
	private String avatar;

	@Column
	private int prioritize;

	@Column
	private String district;

	@Column  Integer numberOfRooms;

	public int getPrioritize() {
		return prioritize;
	}

	public void setPrioritize(int prioritize) {
		this.prioritize = prioritize;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "staff_building", joinColumns = {
			@JoinColumn(name = "building_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "staff_id", nullable = false) })
	private List<UserEntity> staffs = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH })
	@JoinTable(name = "proritize_building", joinColumns = {
			@JoinColumn(name = "building_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "staff_id", nullable = false) })
	private List<UserEntity> staffsPrioritize = new ArrayList<>();


	public List<UserEntity> getStaffsPrioritize() {
		return staffsPrioritize;
	}

	public void setStaffsPrioritize(List<UserEntity> staffsPrioritize) {
		this.staffsPrioritize = staffsPrioritize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getMotorParkingCharge() {
		return motorParkingCharge;
	}

	public void setMotorParkingCharge(String motorParkingCharge) {
		this.motorParkingCharge = motorParkingCharge;
	}

	public String getCarParkingCharge() {
		return carParkingCharge;
	}

	public void setCarParkingCharge(String carParkingCharge) {
		this.carParkingCharge = carParkingCharge;
	}

	public String getExtraCharge() {
		return extraCharge;
	}

	public void setExtraCharge(String extraCharge) {
		this.extraCharge = extraCharge;
	}

	public String getElectricCharge() {
		return electricCharge;
	}

	public void setElectricCharge(String electricCharge) {
		this.electricCharge = electricCharge;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getTimeForRent() {
		return timeForRent;
	}

	public void setTimeForRent(String timeForRent) {
		this.timeForRent = timeForRent;
	}

	public String getTimeForDecorate() {
		return timeForDecorate;
	}

	public void setTimeForDecorate(String timeForDecorate) {
		this.timeForDecorate = timeForDecorate;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getAgencyCharge() {
		return agencyCharge;
	}

	public void setAgencyCharge(String agencyCharge) {
		this.agencyCharge = agencyCharge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public List<UserEntity> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<UserEntity> staffs) {
		this.staffs = staffs;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public Integer getRentArea() {
		return rentArea;
	}

	public void setRentArea(Integer rentArea) {
		this.rentArea = rentArea;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
}
