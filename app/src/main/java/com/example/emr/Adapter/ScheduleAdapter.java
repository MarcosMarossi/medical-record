package com.example.emr.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emr.Models.Scheduling;
import com.example.emr.R;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    private List<Scheduling> list;
    private Context context;

    public ScheduleAdapter(List<Scheduling> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_historic, parent, false);
        return new ScheduleAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.titulo.setText(list.get(position).getDate());
            holder.descricao.setText(list.get(position).getMedic());
            holder.descricao1.setText(list.get(position).getStatus());
            holder.schedule.setText( list.get( position ).get_id() );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView descricao;
        TextView descricao1;
        TextView schedule;

        public MyViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById( R.id.txtTitulo1 );
            descricao = itemView.findViewById(R.id.txtDescricao1 );
            descricao1 = itemView.findViewById(R.id.txtDescricao2 );
            schedule = itemView.findViewById( R.id.txtIdSchedule );

        }
    }

}
