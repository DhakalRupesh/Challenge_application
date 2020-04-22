package com.rupesh.baji.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rupesh.baji.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddChallenge extends AppCompatActivity {

    EditText et_ch_type, et_ch_point, et_ch_date, et_ch_time, et_ch_Description;
    ImageView img_ch_image;
    Button btnAddChallenge;
    final Calendar myCalendar = Calendar.getInstance();
    TimePickerDialog mTimePicker;
    int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
    int minute = myCalendar.get(Calendar.MINUTE);
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);

        et_ch_type = findViewById(R.id.et_challenge_type);
        et_ch_point = findViewById(R.id.et_challenge_point);
        et_ch_date = findViewById(R.id.et_challenge_date);
        et_ch_time = findViewById(R.id.et_challenge_time);
        et_ch_Description = findViewById(R.id.et_challenge_Desc);

        img_ch_image = findViewById(R.id.img_ac_challenge_image);

        btnAddChallenge = findViewById(R.id.btn_ac_addChallenge);

        // date picker
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelDate();
            }
        };

        et_ch_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddChallenge.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // time picker
        et_ch_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePicker = new TimePickerDialog(AddChallenge.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        et_ch_time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        img_ch_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        btnAddChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEmpty()){
                    if(imagePath == null){
                        Toast.makeText(AddChallenge.this, "Please select an image", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });

    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(AddChallenge.this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        img_ch_image.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(AddChallenge.this, uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    // initialize date in text box and format of date
    private void updateLabelDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_ch_date.setText(sdf.format(myCalendar.getTime()));
    }

    private boolean checkEmpty() {
        if(et_ch_type.getText().toString().trim().isEmpty()){
            et_ch_type.setError("this field is required");
            return false;
        }
        if(et_ch_point.getText().toString().trim().isEmpty()){
            et_ch_point.setError("this field is required");
            return false;
        }
        if(et_ch_date.getText().toString().trim().isEmpty()){
            et_ch_date.setError("this field is required");
            return false;
        }
        if(et_ch_time.getText().toString().trim().isEmpty()){
            et_ch_time.setError("this field is required");
            return false;
        }
        if(et_ch_Description.getText().toString().trim().isEmpty()){
            et_ch_time.setError("this field is required");
            return false;
        }
        return true;
    }
}
