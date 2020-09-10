package com.example.lr1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String[] categories = {"Distance", "Weight", "Time"};
    String[] distanceUnits = {"Inch", "Meter", "Centimeter"};
    String[] weightUnits = {"Gram", "Kilogram", "Centner"};
    String[] timeUnits = {"Second", "Minute", "Hour"};
    final boolean[] valuesSwap = {false};

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView versionNameTextView = findViewById(R.id.versionNameTextView);
        versionNameTextView.setText("Application version: " + BuildConfig.VERSION_NAME);

        final Spinner categorySpinner = findViewById(R.id.categorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        final Spinner initalSpinner = findViewById(R.id.initalSpinner);
        final Spinner convertedSpinner = findViewById(R.id.convertedSpinner);

        final TextView initalTextView = findViewById(R.id.inintalTextView);
        final TextView convertedTextView = findViewById(R.id.convertedTextView);
        final Converter converter = new Converter();


        initalTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!initalTextView.getText().equals("") && !valuesSwap[0]) {
                    String initalData = initalTextView.getText().toString();
                    String initalUnit = initalSpinner.getSelectedItem().toString();
                    String convertedUnit = convertedSpinner.getSelectedItem().toString();
                    try {
                        convertedTextView.setText(converter.Convert(initalData, initalUnit, convertedUnit, MainActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (initalTextView.getText().toString().length() == 0) {
                    convertedTextView.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Button button1 = findViewById(R.id.button1);
        button1.setTag(1);

        Button button2 = findViewById(R.id.button2);
        button2.setTag(2);

        Button button3 = findViewById(R.id.button3);
        button3.setTag(3);

        Button button4 = findViewById(R.id.button4);
        button4.setTag(4);

        Button button5 = findViewById(R.id.button5);
        button5.setTag(5);

        Button button6 = findViewById(R.id.button6);
        button6.setTag(6);

        Button button7 = findViewById(R.id.button7);
        button7.setTag(7);

        Button button8 = findViewById(R.id.button8);
        button8.setTag(8);

        Button button9 = findViewById(R.id.button9);
        button9.setTag(9);

        Button button0 = findViewById(R.id.button0);
        button0.setTag(0);

        Button buttonDot = findViewById(R.id.buttonDot);
        buttonDot.setTag('.');

        Button buttonBackspace = findViewById(R.id.buttonBackspace);
        buttonBackspace.setTag("-1");

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                valuesSwap[0] = false;
                if (v.getTag().equals(".")) {
                    initalTextView.append(".");
                } else if (v.getTag().toString().equals("-1")) {
                    if (initalTextView.getText().length() >= 1) {
                        initalTextView.setText(initalTextView.getText().subSequence(0, initalTextView.getText().length() - 1));
                    }
                } else {
                    initalTextView.append(v.getTag().toString());
                }

            }
        };

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        button9.setOnClickListener(onClickListener);
        button0.setOnClickListener(onClickListener);
        buttonBackspace.setOnClickListener(onClickListener);
        buttonDot.setOnClickListener(onClickListener);

        final AdapterView.OnItemSelectedListener categoryItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                String item = (String) parent.getItemAtPosition(index);

                ArrayAdapter<String> initalAdapter;
                ArrayAdapter<String> convertedAdapter;

                switch (item) {
                    case "Weight":
                        initalAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, weightUnits);
                        convertedAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item);
                        break;
                    case "Time":
                        initalAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, timeUnits);
                        convertedAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, timeUnits);
                        break;
                    default:
                        initalAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, distanceUnits);
                        convertedAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, distanceUnits);
                        break;
                }
                initalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                initalSpinner.setAdapter(initalAdapter);
                convertedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                convertedSpinner.setAdapter(initalAdapter);
                convertedSpinner.setSelection(2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };

        categorySpinner.setOnItemSelectedListener(categoryItemSelectedListener);

        final AdapterView.OnItemSelectedListener UnitItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                if (!valuesSwap[0]) {
                    valuesSwap[0] = true;
                    initalTextView.setText("");
                    convertedTextView.setText("");
                    valuesSwap[0] = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        initalSpinner.setOnItemSelectedListener(UnitItemSelectedListener);
        convertedSpinner.setOnItemSelectedListener(UnitItemSelectedListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        valuesSwap[0] = true;
    }
}
