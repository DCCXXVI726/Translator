package com.dccxxvi.translator;

/**
 * Created by Алексей on 17.04.2017.
 */

public class Languages  {
    public static String Shorten (String Language){
        String shorten = new String();
        switch (Language){
            case "Русский":
                shorten = "ru";
                break;
            case "Английский":
                shorten = "en";
        }
        return shorten;
    }
}
