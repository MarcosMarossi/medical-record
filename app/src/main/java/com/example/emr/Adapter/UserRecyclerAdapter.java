package com.example.emr.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emr.Config.HolderUser;
import com.example.emr.Models.User;
import com.example.emr.R;

import java.util.ArrayList;

public class UserRecyclerAdapter extends RecyclerView.Adapter<HolderUser> {

    Context c;
    ArrayList<User> users;

    public UserRecyclerAdapter(Context c, ArrayList<User> users) {
        this.c = c;
        this.users = users;
    }


    @NonNull
    @Override
    public HolderUser onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_patient,null);
        return new HolderUser(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HolderUser holderUser, int i) {
        holderUser.paciente_nome.setText(users.get(i).getName());
        holderUser.paciente_cpf.setText(users.get(i).getCpf());

    }


    @Override
    public int getItemCount() {
        return users.size();
    }
}
