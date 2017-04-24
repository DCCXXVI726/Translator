package com.dccxxvi.translator;

import android.database.sqlite.SQLiteOpenHelper;

import javax.net.ssl.HttpsURLConnection;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;

public class YandexTranslation {
    public static String translate(String lang, String input) {
        try {
            String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20170417T203307Z.b31d06517ea29771.ca435936bff4c46d07ced8023d7c468c5aa5ea80";
            System.out.println(input);
            URL urlObj = new URL(url);
            System.out.println(lang);
            HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes("text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang);
            InputStream response = connection.getInputStream();
            String json = new java.util.Scanner(response).nextLine();
            int start = json.indexOf("[");
            int end = json.indexOf("]");
            String translated = json.substring(start + 2, end - 1);
            return translated;
        } catch (Exception e) {
            e.printStackTrace();
            return "e";
        }
    }
}
