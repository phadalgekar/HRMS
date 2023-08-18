package com.management.HRMS.entity;

import java.time.LocalDateTime;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "emp")

public class Employee {

    @Id
    @Column(name = "eid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;


    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "dob")
    private String dob;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Address is required")
    @Column(name = "address")
    private String address;

    @Column(name = "role")
    private String role;

    @Column(name = "dept")
    private String dept;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "aadharNumber")
    private String aadharNumber;

    @Column(name = "panNumber")
    private String panNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "maritalStatus")
    private String maritalStatus;

    @Column(name = "bloodGroup")
    private String bloodGroup;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "basicSalary")
    private double basicSalary;

    @Column(name = "Type")
    private String Type;

    @Lob
    @Column(name = "img1")
    private byte[] img1;
    private String img1Base64;

    @Column(name = "createdDate")
    private LocalDateTime createdDate; 

    @PrePersist
    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    
    public Long getEid() {
		return eid;
	}


	public void setEid(Long eid) {
		this.eid = eid;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getAadharNumber() {
		return aadharNumber;
	}


	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}


	public String getPanNumber() {
		return panNumber;
	}


	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMaritalStatus() {
		return maritalStatus;
	}


	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public String getBloodGroup() {
		return bloodGroup;
	}


	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public double getBasicSalary() {
		return basicSalary;
	}


	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}


	public String getType() {
		return Type;
	}


	public void setType(String type) {
		Type = type;
	}


	public byte[] getImg1() {
		return img1;
	}


	public void setImg1(byte[] img1) {
		this.img1 = img1;
	}


	public String getImg1Base64() {
		return img1Base64;
	}


	public void setImg1Base64(String img1Base64) {
		this.img1Base64 = img1Base64;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Employee(Long eid, String fname, String lname, String dob, String email, String phone,
			@NotBlank(message = "Address is required") String address, String role, String dept, String nationality,
			String aadharNumber, String panNumber, String gender, String maritalStatus, String bloodGroup,
			String startDate, String endDate, double basicSalary, String type, byte[] img1, String img1Base64,
			LocalDateTime createdDate) {
		super();
		this.eid = eid;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.role = role;
		this.dept = dept;
		this.nationality = nationality;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.bloodGroup = bloodGroup;
		this.startDate = startDate;
		this.endDate = endDate;
		this.basicSalary = basicSalary;
		Type = type;
		this.img1 = img1;
		this.img1Base64 = img1Base64;
		this.createdDate = createdDate;
	}


	@Override
    public String toString() {
        return "Employee [eid=" + eid + ", fname=" + fname + ", lname=" + lname + ", dob=" + dob + ", email=" + email
                + ", phone=" + phone + ", address=" + address + ", role=" + role + ", dept=" + dept + ", nationality="
                + nationality + ", aadharNumber=" + aadharNumber + ", panNumber=" + panNumber + ", gender=" + gender
                + ", maritalStatus=" + maritalStatus + ", bloodGroup=" + bloodGroup + ", startDate=" + startDate
                + ", endDate=" + endDate + ", basicSalary=" + basicSalary + ", Type=" + Type + ", img1="
                + Arrays.toString(img1) + ", img1Base64=" + img1Base64 + ", createdDate=" + createdDate + "]";
    }
}
