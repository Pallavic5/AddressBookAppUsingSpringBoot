package com.bridgelabz.addressbookappbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor

public class AddressBookDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}\\s{0,}[A-z]{1}[a-z]{2,}", message = "First Letter should be capital.")
    private String name;
   @Pattern(regexp = "^[1-9]{2}\\s{0,1}[0-9]{10}$",message ="Phone Number must be filled with country code and 10 digit number.")
    private String phoneNumber;

    private List<String> address;

    private List<String> city;

    private List<String> state;

    private List<String> zipCode;
   @Email(message = "Email should be in email address format")
    private String email;
}
