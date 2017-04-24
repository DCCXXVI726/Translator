package com.dccxxvi.translator;

import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TranslatorActivity extends AppCompatActivity {
    ActionBar actionBar;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    TranslationFragment fragment = new TranslationFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                    actionBar.setTitle("Translator");
                    return true;
                case R.id.navigation_dashboard:
                    HistoryFragment historyfragment = new HistoryFragment();
                    FragmentTransaction hystorytransaction = getFragmentManager().beginTransaction();
                    hystorytransaction.replace(R.id.content, historyfragment);
                    hystorytransaction.commit();
                    actionBar.setTitle("History");
                    return true;
                case R.id.navigation_notifications:
                    FavoriteFragment favoriteFragment = new FavoriteFragment();
                    FragmentTransaction favoritertansaction = getFragmentManager().beginTransaction();
                    favoritertansaction.replace(R.id.content,favoriteFragment);
                    favoritertansaction.commit();
                    actionBar.setTitle("Favorite");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_translator);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        TranslationFragment fragment = new TranslationFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
        actionBar = getSupportActionBar();
        actionBar.show();
    }


}
