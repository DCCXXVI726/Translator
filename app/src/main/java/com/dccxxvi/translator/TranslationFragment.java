package com.dccxxvi.translator;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.text.style.LeadingMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class TranslationFragment extends Fragment {
    private Activity mainActivity ;
    public TranslationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = getActivity();
        return inflater.inflate(R.layout.fragment_translation, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = (Button) mainActivity.findViewById(R.id.button_translate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) mainActivity.findViewById(R.id.text_before_translation);
                String text = editText.getText().toString();
                Spinner spinner_before = (Spinner) mainActivity.findViewById(R.id.before_translation);
                String language_before = String.valueOf(spinner_before.getSelectedItem());
                Spinner spinner_after = (Spinner) mainActivity.findViewById(R.id.after_translation);
                String language_after = String.valueOf(spinner_after.getSelectedItem());
                String lang = Languages.Shorten(language_before) + "-" + Languages.Shorten(language_after);
                Yandex yandex = new Yandex(mainActivity);
                String[] langtext = new String[2] ;
                langtext[0] = lang;
                langtext[1] = text;
                yandex.execute(langtext);
            }
        });
    }


}
