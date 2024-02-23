package com.example.zwiftcars.twilio;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zwiftcars.configuration.TwilioConfig;
import com.example.zwiftcars.entity.DuplicateUser;
import com.example.zwiftcars.mobileOtp.MobileOtp;
import com.example.zwiftcars.mobileOtp.MobileOtpRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {
	@Autowired
	private TwilioConfig twilioConfig;

	@Autowired
	private MobileOtpRepository mobileOtpRepo;
	

	Map<String, String> otpMap = new HashMap<>();

	public OtpResponse sendSMS(DuplicateUser otpRequest) {
		OtpResponse otpResponse = null;
		try {
			PhoneNumber to = new PhoneNumber("+"+otpRequest.getMobileNo());// to
			PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber()); // from
			String otp = generateOTP();
			String otpMessage = "Dear Customer , Your OTP is  " + otp
					+ " for sending sms through Spring boot application. Thank You.";
			Message message = Message.creator(to, from, otpMessage).create();

			MobileOtp twilio = new MobileOtp();
			twilio.setMobileNo(otpRequest.getMobileNo());
			twilio.setOtp(otp);
			twilio.setExpirationTime(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)));
			this.mobileOtpRepo.save(twilio);

			otpResponse = new OtpResponse("DELIVERED", otpMessage);
		} catch (Exception e) {
			e.printStackTrace();
			otpResponse = new OtpResponse("FAILED ",e.getMessage());
		}
		return otpResponse;
	}

	public boolean validateOtp(OtpValidationRequest otpValidationRequest) {

		MobileOtp otp = mobileOtpRepo.findById(otpValidationRequest.getMobileNo()).orElseThrow(null);
		System.out.println("otp: " + otp);

		if (otpValidationRequest.getOtpNumber().equals(otp.getOtp()) && !otp.getExpirationTime().before(new Date())) {
			mobileOtpRepo.deleteById(otpValidationRequest.getMobileNo());
			return true;
		} else {
			return false;
		}
	}
	

	private String generateOTP() {
		return new DecimalFormat("000000").format(new Random().nextInt(999999));
	}
}


