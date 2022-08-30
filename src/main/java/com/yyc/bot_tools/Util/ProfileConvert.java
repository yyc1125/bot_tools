package com.yyc.bot_tools.Util;

import com.yyc.bot_tools.entities.KodaiProfile;
import com.yyc.bot_tools.entities.KylinProfile;
import com.yyc.bot_tools.entities.Profile;


public class ProfileConvert {
    public static Profile kodaiToProfile(KodaiProfile kodaiProfile){
        Profile profile = new Profile();
        profile.setAddress1(kodaiProfile.getBillingAddress().getAddress());
        profile.setAddress2(kodaiProfile.getBillingAddress().getApt());
        profile.setFname(kodaiProfile.getBillingAddress().getFirstName());
        profile.setLname(kodaiProfile.getBillingAddress().getLastName());
        profile.setPhone(kodaiProfile.getBillingAddress().getPhoneNumber());
        profile.setState(kodaiProfile.getBillingAddress().getState());
        profile.setZip(kodaiProfile.getBillingAddress().getZipCode());
        profile.setCardNumber(kodaiProfile.getPaymentDetails().getCardNumber());
        profile.setEmail(kodaiProfile.getPaymentDetails().getEmailAddress());
        profile.setCardExp(kodaiProfile.getPaymentDetails().getExpirationDate());
        profile.setProfileName(kodaiProfile.getProfileName());
        profile.setCountry(kodaiProfile.getRegion());
        profile.setCsv(kodaiProfile.getPaymentDetails().getCvv());
        return profile;
    }

    public static Profile kylinToProfile(KylinProfile kylinProfile){
        Profile profile = new Profile();
        profile.setAddress1(kylinProfile.getDelivery_address1());
        profile.setAddress2(kylinProfile.getDelivery_address2());
        profile.setFname(kylinProfile.getDelivery_fname());
        profile.setLname(kylinProfile.getDelivery_lname());
        profile.setPhone(kylinProfile.getDelivery_phone());
        profile.setState(kylinProfile.getDelivery_state());
        profile.setZip(kylinProfile.getDelivery_zip());
        profile.setCardNumber(kylinProfile.getCard_number());
        profile.setCardExp(kylinProfile.getCard_exp());
        profile.setCsv(kylinProfile.getCard_csc());
        profile.setEmail(kylinProfile.getDelivery_email());
        profile.setProfileName(kylinProfile.getProfile_name());
        profile.setCountry(kylinProfile.getDelivery_country());
        return profile;
    }
}
