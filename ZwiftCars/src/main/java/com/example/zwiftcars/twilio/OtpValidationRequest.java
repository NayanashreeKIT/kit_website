package com.example.zwiftcars.twilio;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpValidationRequest {

   @NotNull
   private String mobileNo;
   @Size(min = 6, max = 6, message = "OTP must contain 6 digits")
   @Pattern(regexp = "^\\d+$", message = "OTP must only contain digits")
	private String otpNumber;
}
