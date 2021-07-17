package com.pg.tho.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table (name="admin")
public class InCharge {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer adminId;
	
	@NotEmpty(message = "Username is Empty -> ENTER USERNAME")
    @Size(min=5,max = 25)
	private String username;
	
	@NotEmpty(message = "Password is Empty -> ENTER PASSWORD")
	@Size(min=8,max = 20)
	private String password;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
