package com.management.HRMS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clid")
	private Long clid;

	@Column(name = "companyName")
	private String companyName;

	@Column(name = "personName")
	private String personName;

	@Column(name = "personPhone")
	private String personPhone;

	@Column(name = "titlePosition")
	private String titlePosition;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "emailId")
	private String emailId;

	public Long getClid() {
		return clid;
	}

	public void setClid(Long clid) {
		this.clid = clid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonPhone() {
		return personPhone;
	}

	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}

	public String getTitlePosition() {
		return titlePosition;
	}

	public void setTitlePosition(String titlePosition) {
		this.titlePosition = titlePosition;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Client [clid=" + clid + ", companyName=" + companyName + ", personName=" + personName + ", personPhone="
				+ personPhone + ", titlePosition=" + titlePosition + ", address=" + address + ", emailId=" + emailId
				+ "]";
	}

	

	
}
