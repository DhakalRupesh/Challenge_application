package com.rupesh.baji.bbl;

import com.rupesh.baji.api.Useri;
import com.rupesh.baji.serverresponse.UserResponse;
import com.rupesh.baji.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBBL {
    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {
        Useri useri = Url.getInstance().create(Useri.class);
        Call<UserResponse> signUpResponseCall =useri.loginUser(username, password);

        try {
            Response<UserResponse> loginResponse = signUpResponseCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

}
