package com.dccxxvi.translator;

/**
 * Created by Алексей on 19.04.2017.
 */

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

public class TranslatorDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "Translator";
    private static final int DB_VERSION = 1;

    TranslatorDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE HISTORY (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "WORD TEXT, "
                + "TRANSLATED TEXT, "
                + "LANG TEXT, "
                + "FAVORITE INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void insertInHistory(SQLiteDatabase db, String word, String translated, String lang, int favorite) {
        if (!word.equals("")) {
            Cursor cursor = db.query("HISTORY",
                    new String[]{"_id", "WORD", "LANG", "FAVORITE"},
                    "WORD = ? AND LANG = ?",
                    new String[]{word, lang},
                    null, null, null);
            if (cursor.moveToFirst()) {
                favorite = cursor.getInt(3);
                db.delete("HISTORY", "WORD = ? AND LANG = ?", new String[]{word, lang});
            }
            ContentValues history = new ContentValues();
            history.put("WORD", word);
            history.put("TRANSLATED", translated);
            history.put("LANG", lang);
            history.put("FAVORITE", favorite);
            db.insert("HISTORY", null, history);
            cursor.close();
        }
    }

    public static int fav(Activity mainActivity, String text, String lang) {
        SQLiteOpenHelper translatordatabase = new TranslatorDatabase(mainActivity);
        SQLiteDatabase db = translatordatabase.getWritableDatabase();

        Cursor cursor = db.query("HISTORY",
                new String[]{"_id", "WORD", "LANG", "FAVORITE"},
                "WORD = ? AND LANG = ?",
                new String[]{text, lang},
                null, null, null);
        int ret = R.drawable.ic_grade_white_24dp;
        ContentValues favValues = new ContentValues();
        if (cursor.moveToFirst()) {
            if (cursor.getInt(3) == R.drawable.ic_grade_black_24dp) {
                favValues.put("FAVORITE", R.drawable.ic_grade_white_24dp);
                System.out.print("дарова");
                ret = R.drawable.ic_grade_white_24dp;
            } else {
                favValues.put("FAVORITE", R.drawable.ic_grade_black_24dp);
                System.out.print("Недарова");
                ret = R.drawable.ic_grade_black_24dp;
            }
            db.update("HISTORY",
                    favValues,
                    "WORD = ? AND LANG = ?",
                    new String[]{text, lang});

        }
        cursor.close();
        db.close();
        return ret;
    }

    public static int fav1(String text, String lang, SQLiteDatabase db) {


        Cursor cursor = db.query("HISTORY",
                new String[]{"_id", "WORD", "LANG", "FAVORITE"},
                "WORD = ? AND LANG = ?",
                new String[]{text, lang},
                null, null, null);
        int ret = R.drawable.ic_grade_white_24dp;
        ContentValues favValues = new ContentValues();
        if (cursor.moveToFirst()) {
            if (cursor.getInt(3) == R.drawable.ic_grade_black_24dp) {
                favValues.put("FAVORITE", R.drawable.ic_grade_white_24dp);
                System.out.print("дарова");
                ret = R.drawable.ic_grade_white_24dp;
            } else {
                favValues.put("FAVORITE", R.drawable.ic_grade_black_24dp);
                System.out.print("Недарова");
                ret = R.drawable.ic_grade_black_24dp;
            }
            db.update("HISTORY",
                    favValues,
                    "WORD = ? AND LANG = ?",
                    new String[]{text, lang});

        }
        cursor.close();
        return ret;
    }


    public static void clear(Activity mainActivity) {
        SQLiteOpenHelper translatordatabase = new TranslatorDatabase(mainActivity);
        SQLiteDatabase db = translatordatabase.getWritableDatabase();
        db.delete("HISTORY", null, null);
    }

}
