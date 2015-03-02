package com.halum.mobileservices;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Halum on 1/19/2015.
 */
public class Constants {
    public static enum A {GP, BLink};
    public static String GP = "Grameenphone";
    public static String BLINK = "Banglalink";
    public static String AIRTEL = "Airtel";
    public static String TTALK = "Teletalk";
    public static String ROBI = "Robi";
    public static String BANKS = "Banks";

    public static Map<String, String> DATABASE = new HashMap<String, String>();
    public static ArrayList<String> ACTIVE_OPERATORS = new ArrayList<String>();
    public static ArrayList<String> OPERATORS = new ArrayList<String>();

    public static void generateDatabase(){
        // ADD ALL OPERATORS FOR TABS
        OPERATORS.clear();
        OPERATORS.add(GP);
        OPERATORS.add(AIRTEL);
        OPERATORS.add(BLINK);
        OPERATORS.add(ROBI);
        OPERATORS.add(TTALK);
        OPERATORS.add(BANKS);

        // BANKS DATA
        DATABASE.put("bKash", "*247");
        DATABASE.put("DBBL", "*");
        DATABASE.put("UCash", "*");

        DATABASE.put(GP+"_my_number", "*2");
        DATABASE.put(AIRTEL+"_my_number", "*121*6*3");
        DATABASE.put(BLINK+"_my_number", "*511");
        DATABASE.put(ROBI+"_my_number", "*140*2*4");
        //DATABASE.put("_my_number", "tar 222");

        DATABASE.put(GP+"_check_balance", "*566");
        DATABASE.put(AIRTEL+"_check_balance", "*778");
        DATABASE.put(BLINK+"_check_balance", "*124");
        DATABASE.put(ROBI+"_check_balance", "*222");
        DATABASE.put(TTALK+"_check_balance", "*152");

        DATABASE.put(GP+"_recharge", "*555*");
        DATABASE.put(AIRTEL+"_recharge", "*787*");
        DATABASE.put(BLINK+"_recharge", "*123*");
        DATABASE.put(ROBI+"_recharge", "*111*");
        DATABASE.put(TTALK+"_recharge", "*151*");


        DATABASE.put(GP+"_callback", "*123*");
        DATABASE.put(BLINK+"_callback", "*126*");
        DATABASE.put(AIRTEL+"_callback", "*121*5");

        DATABASE.put(GP+"_get_emergency_balance", "*1010*1");
        DATABASE.put(BLINK+"_get_emergency_balance", "*874");
        // @TODO airtel ahas another 10tk loan
        DATABASE.put(AIRTEL+"_get_emergency_balance", "*141*10*1");
        DATABASE.put(ROBI+"_get_emergency_balance", "*8811*1");

        DATABASE.put(GP+"_check_emergency_balance", "*566*28");
        DATABASE.put(ROBI+"_check_emergency_balance", "*222*16");
        DATABASE.put(BLINK+"_check_emergency_balance", "*124");
        DATABASE.put(AIRTEL+"_check_emergency_balance", "*778");

        DATABASE.put(GP+"_add_fnf", "*111*2*1*2*");

        DATABASE.put(GP+"_delete_fnf", "");

        DATABASE.put(GP+"_check_fnf", "*111*2*1*1");
    }
}
