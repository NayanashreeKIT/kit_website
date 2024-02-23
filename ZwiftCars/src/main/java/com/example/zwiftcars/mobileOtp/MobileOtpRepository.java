package com.example.zwiftcars.mobileOtp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileOtpRepository extends JpaRepository<MobileOtp, String>{
	

}
