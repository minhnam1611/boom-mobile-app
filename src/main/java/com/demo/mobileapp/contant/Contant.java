package com.demo.mobileapp.contant;

import org.springframework.stereotype.Component;

@Component
public class Contant {
    public static String ROLE_USER = "USER";
    public static String ROLE_ADMIN = "ADMIN";

    public static String STATUS_NO = "N";

    public static class ProcessStatus{
        public static  String STATUS_INIT = "INIT"; //INIT
        public static String STATUS_ACTIVE = "ATV"; //ACTIVE
        public static String STATUS_COM = "COMP"; //COMPLETE
    }
}
