package com.mobile.dental;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mobile.dental.adapter.SpinnerDoctorAdapter;
import com.mobile.dental.base.BaseActivity;
import com.mobile.dental.base.Constant;
import com.mobile.dental.model.Doctor;
import com.mobile.dental.model.PasientResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private Doctor pickedDoctor = null;

    private EditText mDateEdittext;
    private AutoCompleteTextView mDoctorSpinner;
    private final Calendar mCalendar = Calendar.getInstance();
    private AutoCompleteTextView mGolonganDarahSpinner;
    private AutoCompleteTextView mTimeSpinner;
    private EditText mFullnameEditText;
    private EditText mAgeEditText;
    private EditText mKeluhanEditText;
    private Button mSubmitButton;
    private RadioGroup mGenderRadioGroup;
    private RadioButton mPriaRadioButton;
    private RadioButton mWanitaRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init(){
        //inisilaisasi component
        mDateEdittext = findViewById(R.id.ediittext_register);

        //click listener
        mDateEdittext.setOnClickListener(this);

        //initialisespinner
        mDoctorSpinner = findViewById(R.id.spinner_register_doctor);
        mGolonganDarahSpinner = findViewById(R.id.spinner_register_golongan_darah);
        mTimeSpinner = findViewById(R.id.spinner_register_time);

        mFullnameEditText = findViewById(R.id.edittext_register_fullname);
        mAgeEditText = findViewById(R.id.edittext_register_age);
        mKeluhanEditText = findViewById(R.id.edittext_register_keluhan);
        mSubmitButton = findViewById(R.id.button_register_submit);
        mGenderRadioGroup = findViewById(R.id.radioGroup_register_gender);
        mPriaRadioButton = findViewById(R.id.radioButton_register_gender_pria);
        mWanitaRadioButton = findViewById(R.id.radioButton_register_gender_wanita);

        //submit
        mSubmitButton.setOnClickListener(this);

        spinnerSetup();
        golonganDarahSpinnerSetup();
    }

    private void golonganDarahSpinnerSetup() {
        List<String> golonganDarahList = new ArrayList<>();
        golonganDarahList.add("A");
        golonganDarahList.add("B");
        golonganDarahList.add("O");
        golonganDarahList.add("AB");

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, golonganDarahList);
        mGolonganDarahSpinner.setAdapter(adapter);
    }

    private void spinnerSetup() {
        Call<List<Doctor>> getAllDoctorCall = mApiService.getAllDoctor();

        getAllDoctorCall.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if (response.code() != 200) {
                    return;
                }

                if (response.body() == null) {
                    return;
                }

                List<Doctor> doctors = response.body();

                SpinnerDoctorAdapter adapter = new SpinnerDoctorAdapter(RegisterActivity.this, doctors);
                mDoctorSpinner.setAdapter(adapter);

                mDoctorSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        mTimeSpinner.setText("");

                        Doctor doctor = (Doctor) adapterView.getItemAtPosition(i);
                        List<String> timeList = new ArrayList<>(doctor.getJamPraktik());
                        ArrayAdapter timeAdapter = new ArrayAdapter<>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                timeList
                        );

                        mTimeSpinner.setAdapter(timeAdapter);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ediittext_register:
                datePickerDialog(mCalendar, mDateEdittext);
                break;
            case R.id.button_register_submit:
                submit();
                break;
        }
    }

    private void submit() {
        if (pickedDoctor == null){
            toast("Harap isi semua form");
            return;
        }

        if (isEdittextEmpty(mFullnameEditText)
                && isEdittextEmpty(mAgeEditText)
                && isEdittextEmpty(mKeluhanEditText)
                && isEdittextEmpty(mDateEdittext)
                && isEdittextEmpty(mTimeSpinner)) {

            toast("Harap isi semua form");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();

        String fullname = mFullnameEditText.getText().toString();
        String age = mAgeEditText.getText().toString();
        String golonganDarah = mGolonganDarahSpinner.getText().toString();
        String keluhan = mKeluhanEditText.getText().toString();
        String jenisKelamin = getGender();
        String tanggalPendaftaran = dateFormat.format(calendar.getTime());
        String tanggalPelayanan = mDateEdittext.getText().toString();
        String waktuPelayanan = mTimeSpinner.getText().toString();
        String idDokter = pickedDoctor.getId();
        String idUser = mSession.getAuthSession().getId();

        Call<PasientResponse> call = mApiService.postPasient(
                fullname,
                jenisKelamin,
                age,
                golonganDarah,
                keluhan,
                tanggalPendaftaran,
                waktuPelayanan,
                tanggalPelayanan,
                idDokter,
                idUser,
                "1"
        );

        call.enqueue(new Callback<PasientResponse>() {
            @Override
            public void onResponse(Call<PasientResponse> call, Response<PasientResponse> response) {
                if (response.code() == 200) {
                    if (response.body() == null) {
                        toast("Kesalahan Server");
                    } else {
                        toast(response.body().getStatus());
                        finish();
                    }
                } else {
                    toast("Kesalahan Server");
                }
            }

            @Override
            public void onFailure(Call<PasientResponse> call, Throwable t) {

            }
        });
    }

    private String getGender(){
        int id = mGenderRadioGroup.getCheckedRadioButtonId();
        RadioButton button = findViewById(id);

        if (button.getText().toString().equals("Pria"))
            return  "L";
        else
            return  "P";
    }

    private void datePickerDialog(Calendar calendar, EditText editText){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT, Locale.getDefault());

        DatePickerDialog.OnDateSetListener dataSetListener = (datePicker, year, month, dayOfMonth)-> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            editText.setText(simpleDateFormat.format(calendar.getTime()));
        };

        editText.setOnClickListener(view ->{
            DatePickerDialog dialog = new DatePickerDialog(
                    RegisterActivity.this,
                    dataSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get((Calendar.MONTH)),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            dialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis() + 86400000);
            dialog.show();
        });
    }
}