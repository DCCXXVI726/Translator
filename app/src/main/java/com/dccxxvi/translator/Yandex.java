package com.dccxxvi.translator;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by Алексей on 18.04.2017.
 */

public class Yandex extends AsyncTask<String,Void,String> {

    private Activity mainActivity;
    private SQLiteDatabase db;

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
        SQLiteOpenHelper translatorDatabase = new TranslatorDatabase(mainActivity);
        db = translatorDatabase.getWritableDatabase();
        TranslatorDatabase.insertInHistory(db,langtext[1],result,langtext[0],0);
        return result;
    }
}
