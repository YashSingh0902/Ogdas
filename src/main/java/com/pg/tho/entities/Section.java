package com.pg.tho.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table (name="section")
public class Section {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sectionId;
	
	@NotEmpty(message = "Sectionname is Empty -> ENTER SECTIONNAME")
	@Size(min = 2,max = 10)
	private String sectionName;
	
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="addId")
	//private Address address;
	
	@NotEmpty(message = "InChargername is Empty -> ENTER INCHARGERNAME")
	@Size(min = 3,max = 25)
	private String inChargeName;
	@NotEmpty(message = "Email is Empty -> ENTER VALID EMAIL")
	@Size(min = 10)
	@Email(message = "Enter valid email (name@domain.com)")
	private String email;
	@NotEmpty(message = "Password is Empty -> ENTER PASSWORD")
	@Size(min = 8,max = 20)
	private String password;
	//private long contactNumber;

	public Section() {
		super();
	}
	
	public Section( String sectionName,String inChargeName, String email, String password) {
		super();
		this.sectionName = sectionName;
		this.inChargeName = inChargeName;
		this.email = email;
		this.password = password;
		//this.contactNumber=contactNumber;
	}
	
	public Section(int sectionId, String sectionName, String inChargeName, String email, String password) {
		super();
		this.sectionId = sectionId;
		this.sectionName = sectionName;
		this.inChargeName = inChargeName;
		this.email = email;
		this.password = password;
		//this.contactNumber=contactNumber;
		
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}


	public String getInChargeName() {
		return inChargeName;
	}

	public void setInChargeName(String inChargeName) {
		this.inChargeName = inChargeName;
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

	@Override
	public String toString() {
		return "Section [sectionId=" + sectionId + ", sectionName=" + sectionName + ", inChargeName=" + inChargeName
				+ ", email=" + email + ", password=" + password + "]";
	}
	

	/*public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}*/

	
	
	
	
	
	
}

	
