package com.example.emr.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emr.R;

public class AdapterUsr extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    String[][] dados;
    int[] dadosImg;

    public AdapterUsr(Context contexto, String[][] dados, int[] dadosImg) {
        this.contexto = contexto;
        this.dados = dados;
        this.dadosImg = dadosImg;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View view = inflater.inflate(R.layout.list_usr,null);

        TextView titulo = view.findViewById(R.id.txtTitulo);
        TextView descricao = view.findViewById(R.id.txtDescricao);
        ImageView imagem = view.findViewById(R.id.imgMenu);

        titulo.setText(dados[i][0]);
        descricao.setText(dados[i][1]);
        imagem.setImageResource(dadosImg[i]);
        imagem.setTag(i);

        return view;
    }

    @Override
    public int getCount() {
        return dados.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
