package com.example.examenpmul1ev;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TextoFragment extends Fragment {

    private static final String ARG_HINT = "hint";
    private static final String ARG_TEXTS = "texts";
    private static final String ARG_REGEX = "regex";

    public static TextoFragment newInstance(String hint, ArrayList<String> texts, String regex) {
        TextoFragment fragment = new TextoFragment();

        Bundle args = new Bundle();
        args.putString(ARG_HINT, hint);
        args.putStringArrayList(ARG_TEXTS, texts);
        args.putString(ARG_REGEX, regex);

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_texto, container, false);
        EditText editText = view.findViewById(R.id.editText);
        if (getArguments() != null) {
            String hint = getArguments().getString(ARG_HINT);
            ArrayList<String> texts = getArguments().getStringArrayList(ARG_TEXTS);
            String regex = getArguments().getString(ARG_REGEX);
            editText.setHint(hint);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String lowerCaseText = s.toString().toLowerCase();
                    boolean containsText = false;
                    String textoDetectado = "";
                    if (regex != null && !regex.isEmpty()) {
                        Pattern pattern = Pattern.compile(regex);
                        java.util.regex.Matcher matcher = pattern.matcher(lowerCaseText);
                        if (matcher.find()) {
                            containsText = true;
                            textoDetectado = matcher.group();
                        }
                    } else {
                        for (String text : texts) {
                            if (lowerCaseText.contains(text.toLowerCase())) {
                                containsText = true;
                                textoDetectado = text;
                                break;
                            }
                        }
                    }
                    if (containsText) {
                        Intent intent = new Intent(getActivity(), ActivityAlerta.class);
                        intent.putExtra("detectedText", textoDetectado);
                        intent.putExtra("enteredText", lowerCaseText);
                        startActivity(intent);
                    }
                }
            });
        }
        return view;
    }
}