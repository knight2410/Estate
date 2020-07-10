package com.estate.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {
	private static final long serialVersionUID = 3765337930368143532L;
	@Column
	private String name;
	@Column
	private String phoneNumber;
	@Column
	private String email;
	@Column
	private String need;
	@Column
	private String dateMail;
	@Column
	private String process;
	@Column
	private String password;
	@Column
	private String note;
	@Column
	private int status;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_customer", joinColumns = {
			@JoinColumn(name = "customer_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", nullable = false) })
	private List<UserEntity> users;

	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	private List<CustomerServiceEntity> customerService;


	public List<CustomerServiceEntity> getCareDetail() {
		return customerService;
	}

	public void setCareDetail(List<CustomerServiceEntity> careDetail) {
		this.customerService = careDetail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}

	public String getDateMail() {
		return dateMail;
	}

	public void setDateMail(String dateMail) {
		this.dateMail = dateMail;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
