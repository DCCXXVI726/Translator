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
                break;
            case "Белорусский":
                shorten = "be";
                break;
            case "Иврит":
                shorten = "he";
                break;
            case "Китайский":
                shorten = "zh";
                break;
            case "Литовский":
                shorten = "lt";
                break;
            case "Урду":
                shorten = "ur";
                break;
        }
        return shorten;
    }
}
