package com.devtobz.hotelmanagementsystem.Entity.Request;

import com.devtobz.hotelmanagementsystem.Entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.Entity.Enum.IdentificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    private IdentificationType IdentificationType;
    private String phoneNumber;
    private String name;
    private Gender gender;
    private String country;
    private int deposit;

}
