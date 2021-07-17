	package com.pg.tho.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table (name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int customerId;
	@NotEmpty(message = "Customer Name is Empty -> ENTER FULL NAME")
	@Size(min = 4, max = 25)
	private String fullName;
	private String gender;
	@Digits(fraction = 0, integer = 2)
	@Min(value=10)
	private int age;
	@NotEmpty(message = "Mobile Number is Empty -> ENTER MOBILE NO")
	@Size(min=10,max = 10)
	private String mobileNumber;
	@NotEmpty(message = "Mail-Id is Empty -> ENTER MAIL ID")
	@Size(min=10,max = 25)
	@Email(message = "Enter valid email (name@domain.com)")
	private String email;
	@NotEmpty(message = "Password is Empty -> ENTER PASSWORD")
	@Size(min=8,max = 25)
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = false)
	@JoinColumn(name="add_Id", referencedColumnName = "addressId")
    private Address address;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer( String fullName, int age, String email, String password,Address address) {
		super();
		this.fullName = fullName;
		this.gender = gender;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.address=address;
	}

	public Customer(int customerId,	 String fullName,String gender,  int age, String mobileNumber, String email,
			String password, Address address) {
		super();
		this.customerId = customerId;
		this.fullName = fullName;
		this.gender = gender;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.address=address;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", fullName=" + fullName + ", gender=" + gender + ", age=" + age
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", password=" + password + ", address="
				+ address + "]";
	}

	
	
	
}
