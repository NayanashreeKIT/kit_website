package com.example.zwiftcars.mobileOtp;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name="mobileOtp")
public class MobileOtp {
	@Id
	private String mobileNo;
	private String otp;
	private Date expirationTime;
	
}
