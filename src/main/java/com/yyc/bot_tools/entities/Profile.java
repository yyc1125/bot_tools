package com.yyc.bot_tools.entities;

import lombok.Data;

@Data
public class Profile {

    private Integer id;

    private String profileName;

    private String fname;

    private String lname;

    private String email;

    private String phone;

    private String address1;

    private String address2;

    private String zip;

    private String state;

    private String country;

    private String cardNumber;

    private String cardName;

    private String cardExp;

    @Override
    public String toString() {
        return "profile{" +
                "profileName='" + profileName + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
