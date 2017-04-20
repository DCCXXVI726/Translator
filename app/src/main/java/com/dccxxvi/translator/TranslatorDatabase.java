package com.dccxxvi.translator;

/**
 * Created by Алексей on 19.04.2017.
 */
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

public class TranslatorDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "Translator";
    private static final int DB_VERSION = 1;

    TranslatorDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE HISTORY (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "WORD TEXT, "
                    + "TRANSLATED TEXT, "
                    + "LANG TEXT, "
                    + "FAVORITE NUMERIC);");

    }
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
    }
    public static void insertInHistory(SQLiteDatabase db, String word, String translated, String lang, int favorite) {
        ContentValues history = new ContentValues();
        history.put("WORD", word);
        history.put("TRANSLATED",translated);
        history.put("LANG",lang);
        history.put("FAVORITE",favorite);
        db.insert("HISTORY", null,history);
    }
}
