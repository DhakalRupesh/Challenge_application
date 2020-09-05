package com.rupesh.baji.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.activities.Bottom_nav;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.model.User;
import com.rupesh.baji.serverresponse.ImageResponse;
import com.rupesh.baji.strictmode.StrictModeClass;
import com.rupesh.baji.url.Url;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddChallenge extends Fragment {

    EditText et_ch_type, et_ch_game, et_ch_point, et_ch_date, et_ch_time, et_ch_Description;
    ImageView img_ch_image;
    Button btnAddChallenge;
    Spinner selectchallenge;
    final Calendar myCalendar = Calendar.getInstance();
    TimePickerDialog mTimePicker;
    int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
    int minute = myCalendar.get(Calendar.MINUTE);
    String imagePath;
    private String imageName = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_challenge, container, false);

//        et_ch_type = view.findViewById(R.id.et_challenge_type);
        et_ch_game = view.findViewById(R.id.et_challenge_game);
        et_ch_point = view.findViewById(R.id.et_challenge_point);
        et_ch_date = view.findViewById(R.id.et_challenge_date);
        et_ch_time = view.findViewById(R.id.et_challenge_time);
        et_ch_Description = view.findViewById(R.id.et_challenge_Desc);

        selectchallenge = view.findViewById(R.id.et_challenge_type);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.challenge_types,
                R.layout.spinner_color);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        selectchallenge.setAdapter(arrayAdapter);

        img_ch_image = view.findViewById(R.id.img_ac_challenge_image);

        btnAddChallenge = view.findViewById(R.id.btn_ac_addChallenge);

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
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // time picker
        et_ch_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
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
                        Toast.makeText(getContext(), "Please select an image", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    saveImageOnly();
                    if(checkBp()){
                        addNewChallenge();
                    }
                }
//                Toast.makeText(getContext(), "type " + selectchallenge.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // initialize date in text box and format of date
    private void updateLabelDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et_ch_date.setText(sdf.format(myCalendar.getTime()));
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Activity.RESULT_OK) {
            if (data.equals(null)) {
                Toast.makeText(getContext(), "Please select an image ", Toast.LENGTH_SHORT).show();
                return;
            }
            return;
        }
        Uri uri = data.getData();
        img_ch_image.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    public void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

        Challengei challengei = Url.getInstance().create(Challengei.class);
        Call<ImageResponse> responseBodyCall = challengei.uploadChallengeImage(body);

        StrictModeClass.StrictMode();

        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
        } catch (IOException e) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void addNewChallenge(){
        User ChallengeBy = new User(Bottom_nav.user.get_id());
        String ChallengeType = selectchallenge.getSelectedItem().toString();
        String ChallengeGame = et_ch_game.getText().toString().trim();
        String ChallengePoint = et_ch_point.getText().toString().trim();
        String ChallengeDate = et_ch_date.getText().toString().trim();
        String ChallengeTime = et_ch_time.getText().toString().trim();
        String ChallengeDesc = et_ch_Description.getText().toString().trim();
        String Status = "false";
        String confirmation = "not";

        Challenge myChallenge = new Challenge(
                ChallengeType,
                ChallengeBy,
                ChallengeGame,
                ChallengePoint,
                ChallengeDate,
                ChallengeTime,
                ChallengeDesc,
                imageName,
                Status,
                confirmation);

        Challengei mychallengei = Url.getInstance().create(Challengei.class);
        Call<Void> callChallenge = mychallengei.addChallenge(myChallenge);
        callChallenge.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), "Your Challenge is posted successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // update user BP
        int usrBPamt  = Integer.parseInt(Bottom_nav.user.getAmt());
        String fname = Bottom_nav.user.getFname();

        int x = usrBPamt - Integer.parseInt(et_ch_point.getText().toString());
        String remainBp = Integer.toString(x);

        User updateBp = new User(fname, remainBp);

        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> updateCall = useri.updateProfile(Url.token, updateBp);

        updateCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getContext(), "Your Challenge is posted successfully", Toast.LENGTH_SHORT).show();
                ClearField();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Error!! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ClearField() {
//        et_ch_type.setText("");
        et_ch_game.setText("");
        et_ch_point.setText("");
        et_ch_date.setText("");
        et_ch_time.setText("");
        et_ch_Description.setText("");
    }

    private boolean checkEmpty() {
//        if(et_ch_type.getText().toString().trim().isEmpty()){
//            et_ch_type.setError("this field is required");
//            return false;
//        }
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

    private boolean checkBp(){
        int number  = Integer.parseInt(Bottom_nav.user.getAmt());
        int divNumber = Integer.parseInt(et_ch_point.getText().toString().trim());
        try {
            if(number < Integer.parseInt(et_ch_point.getText().toString())){
                et_ch_point.setError("You don't have " + et_ch_point.getText().toString() + " points");
                Toast.makeText(getContext(), "You don't have enough points", Toast.LENGTH_SHORT).show();
                return false;
            }if(Integer.parseInt(et_ch_point.getText().toString()) == 0) {
                et_ch_point.setError("You can't enter 0 points");
                Toast.makeText(getContext(), "You can't enter 0 points", Toast.LENGTH_SHORT).show();
                return false;
            }if(divNumber % 5 != 0) {
                et_ch_point.setError("points should be divisible by 5");
                Toast.makeText(getContext(), "points should be divisible by 5", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException ex){

        }
        return true;
    }

}