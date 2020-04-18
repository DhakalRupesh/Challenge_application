package com.rupesh.baji.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rupesh.baji.R;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.fragments.Home;
import com.rupesh.baji.fragments.Profile;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bottom_nav extends AppCompatActivity {

    BottomNavigationView bnv;
    Fragment selectedFragment = null;
    public static User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        bnv = findViewById(R.id.bottom_nav_menu);
        bnv.setOnNavigationItemSelectedListener(selected_nav_items);
        bnv.setSelectedItemId(R.id.nav_home_menu);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new Home()).commit();

        GetLoggedUserData();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selected_nav_items = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home_menu:
                    selectedFragment = new Home();
                    break;
                case R.id.nav_Profile_menu:
                    selectedFragment = new Profile();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                    selectedFragment).commit();

            if (selectedFragment != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, selectedFragment).commit();
            }
            return true;
        }
    };

    public void GetLoggedUserData(){
        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> userCall = useri.getme(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
//                String imgPath = Url.imagePath + response.body().getProImg();
//                Picasso.get().load(imgPath);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
