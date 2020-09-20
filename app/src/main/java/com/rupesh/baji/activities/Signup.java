package com.rupesh.baji.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.model.User;
import com.rupesh.baji.serverresponse.ImageResponse;
import com.rupesh.baji.strictmode.StrictModeClass;
import com.rupesh.baji.url.Url;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {

    EditText etFullname, etEmail, etUsername, etPassword;
    Button btnSignUp;
    TextView gt_login;
    private ImageView imgProfile;
    String imagePath;
    private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etFullname = findViewById(R.id.et_fullname);
        etEmail = findViewById(R.id.et_email);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        imgProfile = findViewById(R.id.img_register_profile_Image);

        btnSignUp = findViewById(R.id.btn_signup);

        gt_login = findViewById(R.id.tv_goToLogin);

        gt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gt_Login = new Intent(getApplicationContext(), Login.class);
                startActivity(gt_Login);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                    if(imagePath == null){
//                        Toast.makeText(Signup.this, "Please select an image", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
                    saveImageOnly();
                if(CheckEmpty()){
                    RegisterUser();
                }
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
    }

    private void RegisterUser(){
        String fullname = etFullname.getText().toString();
        String email = etEmail.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String gamepoint = "20";
        String phone = "edit profile to add phone number";

        User user = new User(fullname, email, username, password, gamepoint, phone, imageName);

        Useri usersAPI = Url.getInstance().create(Useri.class);
        Call<Void> userCall  = usersAPI.addUser(user);

        System.out.println("Retrofit call is: "+userCall);

        userCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Signup.this, "Error!! " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Signup.this, "Sign-up success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("The error is: "+t.getLocalizedMessage());
                Toast.makeText(Signup.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        Useri usersAPI = Url.getInstance().create(Useri.class);
        Call<ImageResponse> responseBodyCall = usersAPI.registerImage(body);

        StrictModeClass.StrictMode();

        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
//            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public boolean CheckEmpty(){
        if(etFullname.getText().toString().trim().isEmpty()){
            etFullname.setError("Empty field!!");
            return false;
        } if (etEmail.getText().toString().trim().isEmpty()) {
            etEmail.setError("Empty field!!");
            return false;
        } if (etUsername.getText().toString().trim().isEmpty()) {
            etUsername.setError("Empty field!!");
            return false;
        } if (etPassword.getText().toString().trim().isEmpty()) {
            etPassword.setError("Empty field!!");
            return false;
        }
        return true;
    }

    public boolean emailValidation(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        boolean istrue = false;
        if(email.isEmpty()) {
            Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
        }else {
            if (email.trim().matches(emailPattern)) {
                istrue = true;
            } else {
                Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
            }
        }
        return istrue;
    }
}
