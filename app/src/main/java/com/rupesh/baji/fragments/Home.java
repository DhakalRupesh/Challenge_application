package com.rupesh.baji.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.adapters.ChallengeAdapter;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.api.Useri;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    Activity activity = getActivity();
    List<Challenge> challengeList;
    ChallengeAdapter challengeAdapter;
    private static final String TAG = "Home";
    private EditText SearchBP;
    public RecyclerView featuredChallenges;
    Button btnAddNewChallenge;
    TextView playersname;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        featuredChallenges = view.findViewById(R.id.rv_challenge_card);
        btnAddNewChallenge = view.findViewById(R.id.btn_home_addChallenge);
        playersname = view.findViewById(R.id.tv_playersname);
        SearchBP = view.findViewById(R.id.et_home_Search);

        btnAddNewChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), com.rupesh.baji.activities.Challenge.class));
            }
        });

        LoadUserData();
        getAllChallenges();

        SearchBP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        return view;
    }

    private void LoadUserData() {
        Useri useri = Url.getInstance().create(Useri.class);
        Call<User> userCall = useri.getme(Url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: imageError" + response.code());
                    return;
                }
                User user = response.body();
                playersname.setText(user.getFname());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "ofFailure" + t.getLocalizedMessage());
            }
        });
    }


    public void getAllChallenges(){
        Challengei allchallengei = Url.getInstance().create(Challengei.class);
        Call<List<Challenge>> listCall = allchallengei.getAllChallenges(Url.token);

        listCall.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                challengeList = response.body();
                challengeAdapter = new ChallengeAdapter(getContext(), challengeList);
                featuredChallenges.setAdapter(challengeAdapter);
                featuredChallenges.setLayoutManager(new GridLayoutManager(getContext(), 2));
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
                Toast.makeText(activity, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
            }
        });
    }

    // search
    private void filter(String text) {
        ArrayList<Challenge> filteredList=new ArrayList<>();
        for( Challenge item: challengeList){
            if( item.getChAmt().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        challengeAdapter.FilterPlaces(filteredList);
    }

}
