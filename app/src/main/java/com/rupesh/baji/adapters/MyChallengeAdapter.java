package com.rupesh.baji.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rupesh.baji.R;
import com.rupesh.baji.activities.ChallengeDetail;
import com.rupesh.baji.api.Challengei;
import com.rupesh.baji.fragments.MyChallenges;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyChallengeAdapter extends RecyclerView.Adapter<MyChallengeAdapter.MyChallengeViewHolder> {

    Context mContext;
    List<Challenge> challengeList;
    private static User userme;
    private static final String TAG = "ChallengeAdapter";

    public MyChallengeAdapter(Context context, List<Challenge> challengeList) {
        this.mContext = context;
        this.challengeList = challengeList;
    }

    @NonNull
    @Override
    public MyChallengeAdapter.MyChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_mychallenges, parent, false);
        return new MyChallengeAdapter.MyChallengeViewHolder(view, mContext, challengeList);
    }

    public void Mode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChallengeAdapter.MyChallengeViewHolder holder, int position) {
        Challenge challenge = challengeList.get(position);
        Mode();
        userme = challenge.getChBy();
        String imagePathPost = Url.imagePath + challenge.getChImage();
        Picasso.get().load(imagePathPost).into(holder.imgGameProfile);

        holder.tvChallenger.setText(userme.getUname());
        holder.tvGameName.setText(challenge.getChGame());
        holder.tvBP.setText(challenge.getChAmt());

//        holder.imgViewDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent viewDetails = new Intent(mContext, ChallengeDetail.class);
//
//                Challenge challenge = challengeList.get(position);
//                User user;
//                user = challenge.getChBy();
//
//                viewDetails.putExtra("chID", challenge.get_id());
//                viewDetails.putExtra("challenger", user.getUname());
//                viewDetails.putExtra("chEmail", user.getEmail());
//                viewDetails.putExtra("chType", challenge.getChType());
//                viewDetails.putExtra("chGame", challenge.getChGame());
//                viewDetails.putExtra("chBP", challenge.getChAmt());
//                viewDetails.putExtra("chDesc", challenge.getChDesc());
//                viewDetails.putExtra("chTime", challenge.getChTime());
//                viewDetails.putExtra("chDate", challenge.getChDate());
//
//                mContext.startActivity(viewDetails);
//            }
//        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Challengei challengei = Url.getInstance().create(Challengei.class);
                Call<Void> mychallengeDeleteCall = challengei.deleteMyChallenge(Url.token, challenge.get_id());

                mychallengeDeleteCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(mContext, "Error"+ response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(mContext, "Deleted Successfully" , Toast.LENGTH_SHORT).show();
//                        MyChallenges y = new MyChallenges();
//                        y.loadMyChallenges();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(mContext, "Error"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return challengeList.size();
    }

    public class MyChallengeViewHolder extends RecyclerView.ViewHolder {
        ImageView imgGameProfile, imgViewDetail;
        TextView tvChallenger, tvGameName, tvBP;
        ImageView btnDelete;
        Context context;
        List<Challenge> list;

        public MyChallengeViewHolder(@NonNull View itemView, Context mContext, List<Challenge> challengeList) {
            super(itemView);

            imgGameProfile = itemView.findViewById(R.id.img_mychallenge_game_image);
            imgViewDetail = itemView.findViewById(R.id.btn_moreinfo_Challenge);

            tvChallenger = itemView.findViewById(R.id.tv_mychallenge_challengerName);
            tvGameName = itemView.findViewById(R.id.tv_mychallenge_GameName);
            tvBP = itemView.findViewById(R.id.tv_mychallenge_BP);

            btnDelete = itemView.findViewById(R.id.btn_delete_myChallenge);

            this.context = mContext;
            this.list = challengeList;
        }
    }
}
