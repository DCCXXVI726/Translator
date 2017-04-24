package com.dccxxvi.translator;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Алексей on 24.04.2017.
 */

public class MyCursorAdapter extends SimpleCursorAdapter {
    private String text;
    private String lang;
    private SQLiteDatabase db;

    public MyCursorAdapter(Context context, int layout, Cursor cursor, String[] fromColumns, int[] toViews, int flags, SQLiteDatabase _db){
        super(context,layout,cursor,fromColumns,toViews,flags);
        db = _db;
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        final Activity mainActivity = (Activity) view.getParent();
        super.bindView(view,context,cursor);
        TextView textView = (TextView) view.findViewById(R.id.history_before_translate);
        text = textView.getText().toString();
        TextView langView = (TextView) view.findViewById(R.id.history_lang);
        lang = langView.getText().toString();
        final ImageButton fav = (ImageButton) view.findViewById(R.id.fav);
        fav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fav.setImageResource(TranslatorDatabase.fav1(text,lang,db));
            }
        });
    }
}
