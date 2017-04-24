package com.dccxxvi.translator;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by Алексей on 23.04.2017.
 */

public class InsertDatabase extends AsyncTask<String,Void,Void> {

    private Activity mainActivity;
    private SQLiteDatabase db;

    InsertDatabase (Activity activity) {
        mainActivity = activity;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

    @Override
    protected Void doInBackground(String... langtext) {
        String res = YandexTranslation.translate(langtext[0], langtext[1]);
        SQLiteOpenHelper translatorDatabase = new TranslatorDatabase(mainActivity);
        db = translatorDatabase.getWritableDatabase();
        TranslatorDatabase.insertInHistory(db,langtext[1],res,langtext[0],R.drawable.ic_grade_white_24dp);
        return null;
    }
}