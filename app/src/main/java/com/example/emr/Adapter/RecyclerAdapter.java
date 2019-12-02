package com.example.emr.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emr.Config.Holder;
import com.example.emr.Models.User;
import com.example.emr.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<Holder> {

    Context c;
    ArrayList<User> users;

    public RecyclerAdapter(Context c, ArrayList<User> users) {
        this.c = c;
        this.users = users;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_recycler_view,null);


        return new Holder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.bpmLog.setText(users.get(i).getHeartbeat());
        if(Integer.parseInt(holder.bpmLog.getText().toString())>100){
            holder.bpmLog.setTextColor(Color.parseColor("#ff1303"));
        }

        holder.dateLog.setText(users.get(i).getBrdate());
    }


    @Override
    public int getItemCount() {
        return users.size();
    }
}
