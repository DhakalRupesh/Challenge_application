package com.rupesh.baji.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.bbl.LoginBBL;
import com.rupesh.baji.fragments.Home;
import com.rupesh.baji.strictmode.StrictModeClass;
import com.rupesh.baji.url.Url;

public class Login extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView gt_signup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        btnLogin = findViewById(R.id.btn_login);

        gt_signup = findViewById(R.id.tv_goToSignup);

        gt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gt_signup = new Intent(getApplicationContext(), Signup.class);
                startActivity(gt_signup);
            }
        });

        etUsername.requestFocus();

        SharedPreferences sharedPreferences = getSharedPreferences( "Baji", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "empty");
        if (!token.equals("empty")){
            Url.token = token;
            Intent intent = new Intent(Login.this, Bottom_nav.class);
            startActivity(intent);
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
    }

    private void LoginUser(){
        if(CheckEmpty()) {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            LoginBBL loginBBL = new LoginBBL();
            StrictModeClass.StrictMode();

            if(loginBBL.checkUser(username, password))
            {
                SharedPreferences sharedPreferences = getSharedPreferences("Baji",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", Url.token);
                editor.commit();
                Intent intent = new Intent(Login.this, Bottom_nav.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Error!! incorrect username or password", Toast.LENGTH_SHORT).show();
                etUsername.requestFocus();
            }
        }
    }

    public boolean CheckEmpty(){
        if(etUsername.getText().toString().trim().isEmpty()){
            etUsername.setError("Empty field Email!!");
            return false;
        } if (etPassword.getText().toString().trim().isEmpty()) {
            etPassword.setError("Empty field Password!!");
            return false;
        }

        return true;
    }

    public boolean CheckEmail() {
        if (etUsername.getText().toString().trim().matches(emailPattern)) {
            Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public Boolean validInput(String email, String password){
        if(email.trim().equals("") || password.trim().equals("")){
//            Toast.makeText(this, "Empty input fields!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
