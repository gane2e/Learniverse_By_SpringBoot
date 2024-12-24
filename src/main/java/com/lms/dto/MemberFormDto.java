package com.lms.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberFormDto {

    private String loginId;
    private String password;
    private String name;
    private String gender;
    private Date birthdate;
    private String mobileNumber;
    private String address;
    private String email;
    private String kakaoKey;


}
