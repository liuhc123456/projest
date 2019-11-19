package com.example.lenovo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.beans.Bean;
import com.example.lenovo.gao_tk2_1.R;

import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter {
    public Context baseContext;
    List<Bean.ResultsBean> results;

    public Context getBaseContext() {
        return baseContext;
    }

    public void setBaseContext(Context baseContext) {
        this.baseContext = baseContext;
    }

    public List<Bean.ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<Bean.ResultsBean> results) {
        this.results = results;
    }

    public RecyAdapter(Context baseContext, List<Bean.ResultsBean> results) {
        this.baseContext = baseContext;
        this.results = results;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0){
            View view = View.inflate(baseContext, R.layout.item_1, null);
            return new ViewHolder(view);
        }else{
            View view = View.inflate(baseContext, R.layout.item_2, null);
            return new ViewHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int type = getItemViewType(i);
        if (type == 0){
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.desc1.setText(results.get(i).getDesc());
            Glide.with(baseContext).load(results.get(i).getUrl()).into(holder.img1);
        }else{
            ViewHolder1 holder1 = (ViewHolder1) viewHolder;
            holder1.desc.setText(results.get(i).getDesc());
            Glide.with(baseContext).load(results.get(i).getUrl()).into(holder1.img);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aonClick.onClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2 == 0){
            return 0;
        }else {
            return 1;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

          TextView desc1;
          ImageView img1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img);
            desc1 = itemView.findViewById(R.id.desc);
        }
    }
    class ViewHolder1 extends RecyclerView.ViewHolder {
          TextView desc;
          ImageView img;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            desc = itemView.findViewById(R.id.desc);
        }
    }
    private AonClick aonClick;

    public void setAonClick(AonClick aonClick) {
        this.aonClick = aonClick;
    }
    public interface AonClick{
        void onClick(int position);
    }
}
