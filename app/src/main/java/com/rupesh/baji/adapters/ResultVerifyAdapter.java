package com.rupesh.baji.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rupesh.baji.R;
import com.rupesh.baji.activities.ChallengeDetail;
import com.rupesh.baji.model.Challenge;
import com.rupesh.baji.model.Result;
import com.rupesh.baji.model.User;
import com.rupesh.baji.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultVerifyAdapter extends RecyclerView.Adapter<ResultVerifyAdapter.ResultVerifyViewHolder> {

    Context mContext;
    List<Challenge> resultList;
    private static User muser;
    private static User mmuser;

    public ResultVerifyAdapter(Context mContext, List<Challenge> resultList) {
        this.mContext = mContext;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ResultVerifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_result_verification, parent, false);
        return new ResultVerifyViewHolder(view, mContext, resultList);
    }

    public void Mode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultVerifyAdapter.ResultVerifyViewHolder holder, int position) {
        Challenge result = resultList.get(position);
        Mode();
        muser = result.getChBy();
        mmuser = result.getChAcceptedby();

        String imagePathPost = Url.imagePath + result.getChImage();
        Picasso.get().load(imagePathPost).into(holder.imgChallengeResult);

        holder.challenger.setText(muser.getUname());
        holder.game.setText(result.getChGame());
        holder.bpamount.setText(result.getChAmt());

        holder.imgResDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewDetails = new Intent(mContext, ChallengeDetail.class);

                Challenge challenge = resultList.get(position);
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
                viewDetails.putExtra("chStatus", challenge.getStatus());

                mContext.startActivity(viewDetails);
            }
        });

    }

    @Override
    public int getItemCount() {
       return resultList.size();
    }

    public class ResultVerifyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgChallengeResult, imgResDetails, imgReportnChange , imgVerify;
        TextView challenger, game, bpamount;

        Context context;
        List<Challenge> list;
        public ResultVerifyViewHolder(@NonNull View itemView, Context mContext, List<Challenge> resultList) {
            super(itemView);

            imgChallengeResult = itemView.findViewById(R.id.img_result_game_res_image);
            imgResDetails = itemView.findViewById(R.id.btn_result_Challenge_detail);
            imgReportnChange = itemView.findViewById(R.id.btn_report_result);
            imgVerify = itemView.findViewById(R.id.btn_verify_res);

            challenger = itemView.findViewById(R.id.tv_result_challengerName);
            game = itemView.findViewById(R.id.tv_result_GameName);
            bpamount = itemView.findViewById(R.id.tv_result_BP);

            this.context = mContext;
            this.list = resultList;

        }
    }
}
