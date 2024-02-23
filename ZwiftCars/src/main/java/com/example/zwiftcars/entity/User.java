package com.example.zwiftcars.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Name should contain only alphabets")
	private String name;
	
	@Column
	@Email(message = "Invalid email format")
	private String email;
	
	@Column(nullable = false, unique=true)
	@Size(max=13)
	@Pattern(regexp = "^\\+\\d+$", message="invalid phone number")
	private String mobileNo;
	
	@Column(nullable=false)
	@Pattern(regexp = "^(?=.*\\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$",message = "password should contain Minimum 8 characters, at least one uppercase letter, one lowercase letter, one digit, and one special character")
	private String password;

	
	@Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", password='" + password + '\'' +
              
                '}';

}
}

