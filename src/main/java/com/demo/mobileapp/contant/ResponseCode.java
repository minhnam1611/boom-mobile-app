package com.demo.mobileapp.contant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("000", "OK"),

    //Auth
    LOGIN_INVALID("GW001" , "Username or password invalid!"),
    USERNAME_EXISTED("GW002" , " Username has existed"),
    PHONE_NO_EXISTED("GW003" , " Phone number has existed "),
    TOKEN_INVALID("GW004", "Token invalid"),
    OLD_PASS_INVALID("GW005", "Current password invalid!"),
    ACCOUNT_INIT("GW006" , "Account not active, please Change Password now")

    
    ;


    private String code;
    private String desc;
}
