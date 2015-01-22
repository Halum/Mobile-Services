package com.halum.mobileservices;

import android.provider.ContactsContract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Halum on 1/19/2015.
 */
public class Constants {
    public static enum A {GP, BLink};
    public static String GP = "GrameenPhone";
    public static String BLink = "BanglaLink";
    public static String Airtel = "Airtel";
    public static String TTalk = "TeleTalk";
    public static String Robi = "Robi";

    public static Map<String, String> DATABASE = new HashMap<String, String>();

    public static void generateDatabase(){
        DATABASE.put("gp_my_number", "*2");
        DATABASE.put("airtel_my_number", "*121*6*3");
        DATABASE.put("blink_my_number", "*511");
        DATABASE.put("robi_my_number", "*140*2*4");
        //DATABASE.put("ttalk_my_number", "tar 222");

        DATABASE.put("gp_check_balance", "*566");
        DATABASE.put("airtel_check_balance", "*778");
        DATABASE.put("blink_check_balance", "*124");
        DATABASE.put("robi_check_balance", "*222");
        DATABASE.put("ttalk_check_balance", "*152");

        DATABASE.put("gp_recharge", "*555*");
        DATABASE.put("airtel_recharge", "*787*");
        DATABASE.put("blink_recharge", "*123*");
        DATABASE.put("robi_recharge", "*111*");
        DATABASE.put("ttalk_recharge", "*151*");


        DATABASE.put("gp_callback", "*123*");

        DATABASE.put("gp_get_emergency_balance", "*1010*1");

        DATABASE.put("gp_check_emergency_balance", "*566*28");

        DATABASE.put("gp_add_fnf", "*111*2*1*2*");

        DATABASE.put("gp_delete_fnf", "");

        DATABASE.put("gp_check_fnf", "*111*2*1*1");
    }
}
