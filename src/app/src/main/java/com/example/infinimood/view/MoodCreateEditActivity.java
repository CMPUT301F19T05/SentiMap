package com.example.infinimood.view;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.infinimood.R;

public class MoodCreateEditActivity extends AppCompatActivity {
    DatePicker datePicker;
    Spinner moodSpinner;
    EditText reasonInput;
    EditText socialSituInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addeditmood);


    }
}
