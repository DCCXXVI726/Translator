package com.dccxxvi.translator;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.LeadingMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslationFragment extends Fragment {
    private Activity mainActivity;
    private String text_before;
    private int sel_spinner_before;
    private int sel_spinner_after;


    public TranslationFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = getActivity();
        View view = inflater.inflate(R.layout.fragment_translation, container, false);
        Spinner spinner_before = (Spinner) view.findViewById(R.id.before_translation);
        EditText editText = (EditText) view.findViewById(R.id.text_before_translation);
        Spinner spinner_after = (Spinner) view.findViewById(R.id.after_translation);
        if (savedInstanceState != null){
            spinner_before.setSelection(savedInstanceState.getInt("spinner_before"));
            editText.setText(savedInstanceState.getString("text_before"));
            spinner_after.setSelection(savedInstanceState.getInt("spinner_after"));
        }
        return view;
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        Spinner spinner_before = (Spinner) mainActivity.findViewById(R.id.before_translation);
        EditText editText = (EditText) mainActivity.findViewById(R.id.text_before_translation);
        Spinner spinner_after = (Spinner) mainActivity.findViewById(R.id.after_translation);
        sel_spinner_before = spinner_before.getSelectedItemPosition();
        text_before = editText.getText().toString();
        sel_spinner_after = spinner_after.getSelectedItemPosition();
        savedInstanceState.putInt("spinner_before",sel_spinner_before);
        savedInstanceState.putString("text_before",text_before);
        savedInstanceState.putInt("spinner_after",sel_spinner_after);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Spinner spinner_before = (Spinner) mainActivity.findViewById(R.id.before_translation);
        final Spinner spinner_after = (Spinner) mainActivity.findViewById(R.id.after_translation);
        final EditText editText = (EditText) mainActivity.findViewById(R.id.text_before_translation);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String text = editText.getText().toString();
                String language_before = String.valueOf(spinner_before.getSelectedItem());
                String language_after = String.valueOf(spinner_after.getSelectedItem());
                String lang = Languages.Shorten(language_before) + "-" + Languages.Shorten(language_after);
                Yandex yandex = new Yandex(mainActivity);
                String[] langtext = new String[3];
                langtext[0] = lang;
                langtext[1] = text;
                langtext[2] = Languages.Shorten(language_after);
                yandex.execute(langtext);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });
        KeyboardVisibilityEvent.setEventListener(
                getActivity(),
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if (!isOpen) {
                            String text = editText.getText().toString();
                            String language_before = String.valueOf(spinner_before.getSelectedItem());
                            String language_after = String.valueOf(spinner_after.getSelectedItem());
                            String lang = Languages.Shorten(language_before) + "-" + Languages.Shorten(language_after);
                            InsertDatabase insertDatabase = new InsertDatabase(mainActivity);
                            String[] langtext = new String[2];
                            langtext[0] = lang;
                            langtext[1] = text;
                            insertDatabase.execute(langtext);
                        }
                    }
                });
        ImageButton swap = (ImageButton) mainActivity.findViewById(R.id.swap);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int spinner1Index = spinner_before.getSelectedItemPosition();
                spinner_before.setSelection(spinner_after.getSelectedItemPosition());
                spinner_after.setSelection(spinner1Index);
                String text = editText.getText().toString();
                String language_before = String.valueOf(spinner_before.getSelectedItem());
                String language_after = String.valueOf(spinner_after.getSelectedItem());
                String lang = Languages.Shorten(language_before) + "-" + Languages.Shorten(language_after);
                Yandex yandex = new Yandex(mainActivity);
                String[] langtext = new String[3];
                langtext[0] = lang;
                langtext[1] = text;
                langtext[2] = Languages.Shorten(language_after);
                yandex.execute(langtext);
                InsertDatabase insertDatabase = new InsertDatabase(mainActivity);
                insertDatabase.execute(langtext);
            }

        });

        ImageButton fav = (ImageButton) mainActivity.findViewById(R.id.make_fav);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton fav = (ImageButton) mainActivity.findViewById(R.id.make_fav);
                String text = editText.getText().toString();
                String language_before = String.valueOf(spinner_before.getSelectedItem());
                String language_after = String.valueOf(spinner_after.getSelectedItem());
                String lang = Languages.Shorten(language_before) + "-" + Languages.Shorten(language_after);
                fav.setImageResource(TranslatorDatabase.fav(mainActivity, text, lang));
            }
        });
        spinner_after.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = editText.getText().toString();
                String language_before = String.valueOf(spinner_before.getSelectedItem());
                String language_after = String.valueOf(spinner_after.getSelectedItem());
                String lang = Languages.Shorten(language_before) + "-" + Languages.Shorten(language_after);
                Yandex yandex = new Yandex(mainActivity);
                String[] langtext = new String[3];
                langtext[0] = lang;
                langtext[1] = text;
                langtext[2] = Languages.Shorten(language_after);
                yandex.execute(langtext);
                InsertDatabase insertDatabase = new InsertDatabase(mainActivity);
                insertDatabase.execute(langtext);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner_before.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = editText.getText().toString();
                String language_before = String.valueOf(spinner_before.getSelectedItem());
                String language_after = String.valueOf(spinner_after.getSelectedItem());
                String lang = Languages.Shorten(language_before) + "-" + Languages.Shorten(language_after);
                Yandex yandex = new Yandex(mainActivity);
                String[] langtext = new String[3];
                langtext[0] = lang;
                langtext[1] = text;
                langtext[2] = Languages.Shorten(language_after);
                yandex.execute(langtext);
                InsertDatabase insertDatabase = new InsertDatabase(mainActivity);
                insertDatabase.execute(langtext);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //todo сделать перевод при нажатии на спинер

    }
}
