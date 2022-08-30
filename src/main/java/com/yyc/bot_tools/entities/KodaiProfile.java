package com.yyc.bot_tools.entities;

import lombok.Data;

@Data
public class KodaiProfile {
    private billingAddress billingAddress;
    private deliveryAddress deliveryAddress;
    private miscellaneousInformation miscellaneousInformation;
    private paymentDetails paymentDetails;
    private String profileName;
    private String region;

    @Data
    public static class billingAddress{
        private String address;
        private String apt;
        private String city;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String state;
        private String zipCode;
    }
    @Data
    public static class deliveryAddress{
        private String address;
        private String apt;
        private String city;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String state;
        private String zipCode;
    }

    @Data
    public static class miscellaneousInformation{
        private Boolean deliverySameAsBilling;
    }

    @Data
    public static class paymentDetails{
        private String cardHolder;
        private String cardNumber;
        private String cvv;
        private String emailAddress;
        private String expirationDate;
    }
}
