package com.example.emr.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.emr.Config.HolderUser;
import com.example.emr.Models.User;
import com.example.emr.R;

import java.util.ArrayList;
import java.util.Collection;

public class UserRecyclerAdapter extends RecyclerView.Adapter<HolderUser> implements Filterable {

    Context c;
    ArrayList<User> usersList;
    ArrayList<User> filteredUsers;


    public UserRecyclerAdapter(Context c, ArrayList<User> users) {
        this.c = c;
        this.usersList = users;
        this.filteredUsers = users;
    }


    @NonNull
    @Override
    public HolderUser onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_patient,null);
        return new HolderUser(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HolderUser holderUser, int i) {
        holderUser.paciente_nome.setText(usersList.get(i).getName());
        holderUser.paciente_cpf.setText(usersList.get(i).getCpf());

    }


    @Override
    public int getItemCount() {
        return filteredUsers.size();
        //return usersAll.size();

    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<User> filteredUsersAux = new ArrayList<>();



            if(charSequence.toString().isEmpty()||charSequence.length()==0){
                filteredUsers  = usersList;

            }else{
                for(User user:usersList){
                    if(user.getCpf().contains(charSequence.toString())){
                        filteredUsersAux.add(user);
                    }
                }
                filteredUsers = filteredUsersAux;
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredUsers;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredUsers = (ArrayList<User>)filterResults.values;
            notifyDataSetChanged();


        }
    };




}
