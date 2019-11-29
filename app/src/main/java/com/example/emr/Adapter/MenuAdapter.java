package com.example.emr.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emr.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private static LayoutInflater inflater = null;

    Context contexto;
    int[][] dados;
    int[] dadosImg;

    public MenuAdapter(Context contexto, int[][] dados, int[] dadosImg) {
        this.contexto = contexto;
        this.dados = dados;
        this.dadosImg = dadosImg;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menu, parent, false);
        return new MenuAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        holder.titulo.setText(dados[i][0]);
        holder.descricao.setText(dados[i][1]);
        holder.imagem.setImageResource(dadosImg[i]);
        holder.imagem.setTag(i);


    }

    @Override
    public int getItemCount() {
        return dados.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView descricao;
        ImageView imagem;

        public MyViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById( R.id.txtTitulo1 );
            descricao = itemView.findViewById(R.id.txtDescricao1 );
            imagem = itemView.findViewById(R.id.imgMenu );

        }
    }
}
