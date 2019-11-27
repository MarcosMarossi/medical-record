package com.example.emr.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emr.Models.Scheduling;
import com.example.emr.R;
import java.util.List;

public class AdapterScheduling extends RecyclerView.Adapter<AdapterScheduling.MyViewHolder> {

    private List<Scheduling> list;
    private Context context;

    public AdapterScheduling(List<Scheduling> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setSchedule(List<Scheduling> schedule) {
        this.list = schedule;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter, parent, false);
        return new AdapterScheduling.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.titulo.setText(list.get(position).getDate());
            holder.descricao.setText(list.get(position).getMedic());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView descricao;

        public MyViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById( R.id.txtTitulo1 );
            descricao = itemView.findViewById(R.id.txtDescricao1 );

        }
    }

}