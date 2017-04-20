package com.dccxxvi.translator;


import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class HistoryFragment extends ListFragment {
    private SQLiteDatabase db;
    private Cursor cursor;
    private Activity mainActivity;

    public HistoryFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mainActivity = getActivity();
        try{
            SQLiteOpenHelper translatorDatabase = new TranslatorDatabase(mainActivity);
            db = translatorDatabase.getReadableDatabase();

            cursor = db.query("HISTORY",
                    new String[]{"_id", "WORD"},
                    null,null,null,null,null);
            CursorAdapter listAdapter = new SimpleCursorAdapter(mainActivity,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"WORD"},
                    new int[]{android.R.id.text1},
                    0);
            setListAdapter(listAdapter);
        }catch (SQLiteException e) {
            Toast toast = Toast.makeText(mainActivity, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    //TODO: Сделать чтобы не было повторений,заняться интерфейсом красивым

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

}
