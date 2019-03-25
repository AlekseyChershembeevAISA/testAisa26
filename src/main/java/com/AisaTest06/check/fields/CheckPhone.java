package com.AisaTest06.check.fields;

@SuppressWarnings("ALL")
public class CheckPhone {

    public static boolean isValidPhone(String phone){

        return phone.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
    }

}
