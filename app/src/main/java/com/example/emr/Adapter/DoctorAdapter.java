package com.example.emr.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emr.Models.Scheduling;
import com.example.emr.Models.User;
import com.example.emr.R;

import java.util.List;

public class DoctorAdapter  extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> {

    private List<Scheduling> schedule;
    private List<User> user;
    private Context context;

    public DoctorAdapter(List<Scheduling> schedule, List<User> user, Context context) {
        this.schedule = schedule;
        this.user = user;
        this.context = context;
    }

    @Override
    public DoctorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.list_historic, parent, false);
        return new DoctorAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctorAdapter.MyViewHolder holder, int position) {
        holder.titulo.setText(user.get(position).getName());
        holder.descricao.setText(schedule.get(position).getDate());
        holder.schedule.setText( schedule.get( position ).get_id() );

    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView descricao;
        TextView schedule;

        public MyViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById( R.id.txtTitulo1 );
            descricao = itemView.findViewById(R.id.txtDescricao1 );
            schedule = itemView.findViewById( R.id.txtIdSchedule );

        }
    }
}
