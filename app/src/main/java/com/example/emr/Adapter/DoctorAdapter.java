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
    private Context context;

    public DoctorAdapter(List<Scheduling> schedule, Context context) {
        this.schedule = schedule;
        this.context = context;
    }

    @Override
    public DoctorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.list_doctor, parent, false);
        return new DoctorAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctorAdapter.MyViewHolder holder, int position) {
        holder.titulo.setText(schedule.get(position).getDate());
        holder.descricao1.setText(schedule.get(position).getPatient());
        holder.descricao2.setText( schedule.get( position ).getStatus());
        holder.descricao3.setText( schedule.get( position ).getMedic() );

    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView descricao1;
        TextView descricao2;
        TextView descricao3;

        public MyViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById( R.id.txtTitulo1 );
            descricao1 = itemView.findViewById(R.id.txtDescricao1 );
            descricao2 = itemView.findViewById( R.id.txtDescricao2 );
            descricao3 = itemView.findViewById( R.id.txtDescricao3 );

        }
    }
}
