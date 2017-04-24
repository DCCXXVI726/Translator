package com.dccxxvi.translator;

/**
 * Created by Алексей on 23.04.2017.
 */

import android.provider.Settings;
import android.widget.ArrayAdapter;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class YandexDictionary {
    private static String urlDictionaryStr = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1.20170423T105452Z.149b2dd170f7836e.d53c7c03f0e7c916648bac0f7057ce509020339d";

    YandexDictionary() {}

    public static String findInDictionary(String text, String language) {
        String inputLang = language + "-" + language;
        System.out.print("Язык равен ="+ language);
        try {


            URL urlDictionary = new URL(urlDictionaryStr);
            HttpsURLConnection connection = (HttpsURLConnection) urlDictionary.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes("&lang=" + inputLang + "&text=" + URLEncoder.encode(text, "UTF-8"));
            InputStream response = connection.getInputStream();

            String json = new java.util.Scanner(response).nextLine();
            String result = JsonParser.interpretingDictionary(json);

            return result;

        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }
    }

}