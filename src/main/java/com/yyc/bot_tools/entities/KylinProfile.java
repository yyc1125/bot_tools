package com.yyc.bot_tools.entities;

import lombok.Data;

@Data
public class KylinProfile {
    private String group;
    private String delivery_fname;
    private String delivery_alt_fname;
    private String delivery_lname;
    private String delivery_alt_lname;
    private String delivery_email;
    private String delivery_phone;
    private String delivery_address1;
    private String delivery_address2;
    private String delivery_address3;
    private String delivery_house_number;
    private String delivery_city;
    private String delivery_state;
    private String delivery_country;
    private String delivery_zip;
    private String billing_fname;
    private String billing_alt_fname;
    private String billing_lname;
    private String billing_alt_lname;
    private String billing_email;
    private String billing_phone;
    private String billing_address1;
    private String billing_address2;
    private String billing_address3;
    private String billing_house_number;
    private String billing_city;
    private String billing_state;
    private String billing_country;
    private String billing_zip;
    private String card_type;
    private String card_number;
    private String card_name;
    private String card_exp;
    private String card_csc;
    private String national_id;
    private Boolean delivery_info_as_billing_info;
    private Boolean template;
    private Boolean one_checkout_only;
    private String card_expiry_month;
    private String card_expiry_year;
    private String card_expiry_full_year;
    private String dob;
    private String delivery_name;
    private String billing_name;
    private Boolean billing_info_as_billing_info;
    private String profile_name;
    private String profile_id;

}
