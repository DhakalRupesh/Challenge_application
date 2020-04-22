package com.rupesh.baji.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rupesh.baji.R;
import com.rupesh.baji.model.Challenge;

import java.util.List;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder> {

    Context mContext;
    List<Challenge> challengeList;

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

    @Override
    public void onBindViewHolder(@NonNull ChallengeViewHolder holder, int position) {
        Challenge challenge = challengeList.get(position);
        holder.imgGameProfile.setImageResource(challenge.getChImage());
        holder.tvChallenger.setText(challenge.getChBy());
    }

    @Override
    public int getItemCount() {
        return challengeList.size();
    }

    public class ChallengeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgGameProfile, imgViewDetail;
        TextView tvChallenger, tvDetails;
        Button btnDetail;

        public ChallengeViewHolder(@NonNull View itemView, Context mContext, List<Challenge> challengeList) {
            super(itemView);

            imgGameProfile = itemView.findViewById(R.id.img_game_image);

            tvChallenger = itemView.findViewById(R.id.tv_challengerName);
//            tvDetails = itemView.findViewById(R.id.tv_view_challenge_detail);
            imgViewDetail = itemView.findViewById(R.id.imgViewDetails);
        }

    }
}
