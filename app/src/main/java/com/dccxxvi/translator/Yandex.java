package com.dccxxvi.translator;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.StringTokenizer;

/**
 * Created by Алексей on 18.04.2017.
 */

public class Yandex extends AsyncTask<String,Void,String> {

    private Activity mainActivity;

    Yandex (Activity activity) {
        mainActivity = activity;
    }

    @Override
    protected void onPostExecute(String result) {
        TextView textView = (TextView) mainActivity.findViewById(R.id.text_after_translation);
        textView.setText(result);
    }

    @Override
    protected String doInBackground(String... langtext) {
        String result = YandexTranslation.translate(langtext[0], langtext[1]);
        StringTokenizer st = new StringTokenizer(langtext[1]);
        int words = st.countTokens();
        if (words==1)
        {
            result = result + YandexDictionary.findInDictionary(result,langtext[2]);
        }
        return result;
    }
}
