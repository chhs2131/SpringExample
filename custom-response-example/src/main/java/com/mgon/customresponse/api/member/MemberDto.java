package com.mgon.customresponse.api.member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String website;
}
