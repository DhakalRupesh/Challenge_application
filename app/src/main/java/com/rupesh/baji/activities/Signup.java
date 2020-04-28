package com.rupesh.baji.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {

    EditText etFullname, etEmail, etUsername, etPassword;
    Button btnSignUp;
    TextView gt_login;
    private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etFullname = findViewById(R.id.et_fullname);
        etEmail = findViewById(R.id.et_email);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

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
                RegisterUser();
            }
        });
    }

    private void RegisterUser(){
        String fullname = etFullname.getText().toString();
        String email = etEmail.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String gamepoint = "20";

        User user = new User(fullname, email, username, password, gamepoint, imageName);

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
}
