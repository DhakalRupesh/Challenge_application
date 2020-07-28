package com.rupesh.baji.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.rupesh.baji.R;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.fragments.Home;
import com.rupesh.baji.fragments.Profile;
import com.rupesh.baji.fragments.Store;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bottom_nav extends AppCompatActivity {

    BottomNavigationView bnv;
    ChipNavigationBar cnb;
    Fragment selectedFragment = null;
    public static User user;
    private int STORAGE_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

//        bnv = findViewById(R.id.bottom_nav_menu);
//        bnv.setOnNavigationItemSelectedListener(selected_nav_items);
//        bnv.setSelectedItemId(R.id.nav_home_menu);

//        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new Home()).commit();
        cnb = findViewById(R.id.bottom_cnv_nav_menu);

        if(savedInstanceState == null) {
            cnb.setItemSelected(R.id.nav_home_menu, true);
            FragmentManager fragmentManager = getSupportFragmentManager();
            Home home = new Home();
            fragmentManager.beginTransaction().replace(R.id.container_fragment, home)
                    .commit();
        }

        cnb.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                switch (id) {
                    case R.id.nav_home_menu:
                        selectedFragment = new Home();
                        break;
                    case R.id.nav_Challenge_menu:
                        selectedFragment = new Store();
                        break;
                    case R.id.nav_Profile_menu:
                        selectedFragment = new Profile();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, selectedFragment).commit();
                if (selectedFragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, selectedFragment).commit();
                }

            }
        });

        UserPermission();
        GetLoggedUserData();
//        Home home = new Home();
//        home.getAllChallenges();
    }

//    private BottomNavigationView.OnNavigationItemSelectedListener selected_nav_items = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.nav_home_menu:
//                    selectedFragment = new Home();
//                    break;
//                case R.id.nav_Challenge_menu:
//                    selectedFragment = new Challenges();
//                    break;
//                case R.id.nav_Profile_menu:
//                    selectedFragment = new Profile();
//                    break;
//            }
//            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
//                    selectedFragment).commit();
//
//            if (selectedFragment != null){
//                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, selectedFragment).commit();
//            }
//            return true;
//        }
//    };


    private void UserPermission() {
        if(ContextCompat.checkSelfPermission( this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        } else {
            requestStoragePermission();
        }
    }

    private void requestStoragePermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this).setTitle("permission needed").setMessage("This permission is needed to save and read image")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(Bottom_nav.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this , "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void GetLoggedUserData(){

        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> userCall = useri.getme(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
