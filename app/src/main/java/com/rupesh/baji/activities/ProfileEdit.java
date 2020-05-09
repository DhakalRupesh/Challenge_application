package com.rupesh.baji.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rupesh.baji.R;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.fragments.Profile;
import com.rupesh.baji.helper.ImgStrictMode;
import com.rupesh.baji.model.User;
import com.rupesh.baji.serverresponse.ImageResponse;
import com.rupesh.baji.strictmode.StrictModeClass;
import com.rupesh.baji.url.Url;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileEdit extends AppCompatActivity {

    EditText etFullname, etUsername, etEmail;
    Button btnUpdate;
    private String imageName = Bottom_nav.user.getProImg();
    private ImageView imgProfile;
    private static final String TAG = "UpdateProfile";
    String imagePath;
//    private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        etFullname = findViewById(R.id.et_ep_fullname);
        etEmail = findViewById(R.id.et_ep_email);
        etUsername = findViewById(R.id.et_ep_username);


        imgProfile = findViewById(R.id.img_ep_profile_Image);

        btnUpdate = findViewById(R.id.btn_ep_update);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(imagePath == null){
//                    Toast.makeText(ProfileEdit.this, "Please select an image", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                UpdateUserInfo();
                getUser();
            }
        });
        getUser();
    }

    private void getUser() {
        etFullname.setText(Bottom_nav.user.getFname());
        etEmail.setText(Bottom_nav.user.getEmail());
        etUsername.setText(Bottom_nav.user.getUname());

        String imgPath = Url.imagePath + Bottom_nav.user.getProImg();
        Picasso.get().load(imgPath).into(imgProfile);
    }

    private void UpdateUserInfo() {
        android.util.Log.e(TAG, "updateProfile image: " + imageName);
        String fullname = etFullname.getText().toString().trim();
        String Email = etEmail.getText().toString().trim();
        String Username = etUsername.getText().toString().trim();

        User userUpdate = new User(fullname, Email, Username, imageName);

        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> updateCall = useri.updateProfile(Url.token, userUpdate);

        updateCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ProfileEdit.this, "Error updating profile", Toast.LENGTH_SHORT).show();
                    return;
                }

//                Intent intentProfile = new Intent(ProfileEdit.this, Bottom_nav.class);
//                startActivity(intentProfile);
                Toast.makeText(ProfileEdit.this, "Profile Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ProfileEdit.this, "Error!! " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
        } else {
            Uri uri = data.getData();
            imgProfile.setImageURI(uri);
            imagePath = getRealPathFromUri(uri);
        }
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
        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body, Url.token);

        ImgStrictMode.ImgMode();

        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            assert imageResponseResponse.body() != null;
            imageName = imageResponseResponse.body().getFilename();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if (imageName.equals("")) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
