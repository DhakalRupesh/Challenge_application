package com.rupesh.baji.adapters;

import android.content.Context;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rupesh.baji.R;
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

//        holder.challenger.setText(muser.getUname());
        holder.game.setText(result.getChGame());
        holder.bpamount.setText(result.getChAmt());

    }

    @Override
    public int getItemCount() {
       return resultList.size();
    }

    public class ResultVerifyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgChallengeResult, imgDEscription, imgReportnChange , imgVerify;
        TextView challenger, game, bpamount;

        Context context;
        List<Challenge> list;
        public ResultVerifyViewHolder(@NonNull View itemView, Context mContext, List<Challenge> resultList) {
            super(itemView);

            imgChallengeResult = itemView.findViewById(R.id.img_result_game_res_image);
            imgDEscription = itemView.findViewById(R.id.btn_result_Challenge_detail);
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
