package com.mobile.dental;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mobile.dental.base.Constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mDateEdittext;
    private final Calendar mCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init() {
        //untuk inisialisasi component
        mDateEdittext = findViewById(R.id.edittext_register_date);


        //click listener
        mDateEdittext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edittext_register_date:
                datePickerDialog(mCalendar, mDateEdittext);
                break;
        }
    }

    private void datePickerDialog(Calendar calendar, EditText editText) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT, Locale.getDefault());

        //configure datepicker listener
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            //set new date on edittext
            editText.setText(simpleDateFormat.format(calendar.getTime()));
        };

        //edittext on clicked
        editText.setOnClickListener(view -> {

            DatePickerDialog dialog = new DatePickerDialog(
                    RegisterActivity.this,
                    dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );

            dialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis() + 86400000 /* One day in millis time */);

            dialog.show();
        });
    }
}