package com.example.emr.Config;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.emr.R;

public class HolderUser extends RecyclerView.ViewHolder {
    public TextView paciente_nome,paciente_cpf;

    public HolderUser(@NonNull View itemView) {
        super(itemView);
        this.paciente_cpf = itemView.findViewById(R.id.paciente_cpf);
        this.paciente_nome = itemView.findViewById(R.id.paciente_nome);
    }
}
