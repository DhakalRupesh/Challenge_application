package com.rupesh.baji.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rupesh.baji.R;
import com.rupesh.baji.adapters.CurrentChallengeAdapter;
import com.rupesh.baji.adapters.ResultVerifyAdapter;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.api.Resulti;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.model.Result;
import com.rupesh.baji.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Result_Verification extends Fragment {

    List<Challenge> resultList;
    ResultVerifyAdapter resultVerifyAdapter;
    private static final String TAG = "ChallengResult";
    RecyclerView rv_resultverification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_result__verification, container, false);

        rv_resultverification = view.findViewById(R.id.rv_accepted_result_verification);

        loadMyVerificationResult();

        return view;
    }

//    public void loadMyVerificationResult () {
//        Resulti verifyResult = Url.getInstance().create(Resulti.class);
//        Call<List<Result>> listCallResult = verifyResult.getResultForVerification(Url.token);
//
//        listCallResult.enqueue(new Callback<List<Result>>() {
//            @Override
//            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                resultList = response.body();
//                resultVerifyAdapter = new ResultVerifyAdapter(getContext(), resultList);
//                rv_resultverification.setAdapter(resultVerifyAdapter);
//                rv_resultverification.setLayoutManager(new LinearLayoutManager(getContext()));
//            }
//
//            @Override
//            public void onFailure(Call<List<Result>> call, Throwable t) {
//                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
//            }
//        });
//
//    }

    public void loadMyVerificationResult () {
        try{
            Challengei verifyChallenge = Url.getInstance().create(Challengei.class);
            Call<List<Challenge>> acceptedChallengeCall = verifyChallenge.getChallengeForVerification(Url.token);

            acceptedChallengeCall.enqueue(new Callback<List<Challenge>>() {
                @Override
                public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    resultList = response.body();
                    resultVerifyAdapter = new ResultVerifyAdapter(getContext(), resultList);
                    rv_resultverification.setAdapter(resultVerifyAdapter);
                    rv_resultverification.setLayoutManager(new LinearLayoutManager(getContext()));
                }

                @Override
                public void onFailure(Call<List<Challenge>> call, Throwable t) {
                    Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onFailureHome: " + t.getLocalizedMessage());
                }
            });
        } catch (IllegalStateException e) {

        }
    }
}