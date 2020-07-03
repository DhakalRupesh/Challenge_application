package com.rupesh.baji.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rupesh.baji.R;
import com.rupesh.baji.activities.ChallengeDetail;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder> {

    Context mContext;
    List<Challenge> challengeList;
    private static User userme;
    private static final String TAG = "ChallengeAdapter";

    public ChallengeAdapter(Context context, List<Challenge> challengeList) {
        this.mContext = context;
        this.challengeList = challengeList;
    }

    @NonNull
    @Override
    public ChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_home, parent, false);
        return new ChallengeViewHolder(view, mContext, challengeList);
    }

    public void Mode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeViewHolder holder, final int position) {
        Challenge challenge = challengeList.get(position);
        Mode();
        userme = challenge.getChBy();
        String imagePathPost = Url.imagePath + challenge.getChImage();
        Picasso.get().load(imagePathPost).into(holder.imgGameProfile);

        holder.tvChallenger.setText(userme.getUname());
        holder.tvGameName.setText(challenge.getChGame());
        holder.tvBP.setText(challenge.getChAmt());
        holder.tvchId.setText(challenge.get_id());

        holder.imgViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewDetails = new Intent(mContext, ChallengeDetail.class);

                Challenge challenge = challengeList.get(position);
                User user;
                user = challenge.getChBy();

                viewDetails.putExtra("chID", challenge.get_id());
                viewDetails.putExtra("challenger", user.getUname());
                viewDetails.putExtra("userID", user.get_id());
                viewDetails.putExtra("chEmail", user.getEmail());
                viewDetails.putExtra("chType", challenge.getChType());
                viewDetails.putExtra("chGame", challenge.getChGame());
                viewDetails.putExtra("chBP", challenge.getChAmt());
                viewDetails.putExtra("chDesc", challenge.getChDesc());
                viewDetails.putExtra("chTime", challenge.getChTime());
                viewDetails.putExtra("chDate", challenge.getChDate());

                mContext.startActivity(viewDetails);
            }
        });
    }

    public void FilterPlaces(ArrayList<Challenge> filteredList){
        challengeList=filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return challengeList.size();
    }

    public class ChallengeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgGameProfile, imgViewDetail;
        TextView tvChallenger, tvGameName, tvBP, tvchId;
        Context context;
        List<Challenge> list;

        public ChallengeViewHolder(@NonNull View itemView, Context mContext, List<Challenge> challengeList) {
            super(itemView);

            imgGameProfile = itemView.findViewById(R.id.img_game_image);
            tvChallenger = itemView.findViewById(R.id.tv_challengerName);
            tvGameName = itemView.findViewById(R.id.tv_GameName);
            tvBP = itemView.findViewById(R.id.tv_BP);
            tvchId = itemView.findViewById(R.id.tv_chID);
            imgViewDetail = itemView.findViewById(R.id.imgViewDetails);

            this.context = mContext;
            this.list = challengeList;

        }
    }
}
