package com.example.emr.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emr.Models.User;
import com.example.emr.R;

import java.util.ArrayList;

public class AdapterUser extends BaseAdapter {

    private final ArrayList<User> users;
    private final Activity act;

    public AdapterUser(ArrayList<User> users, Activity act) {
        this.users = users;
        this.act = act;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cview = act.getLayoutInflater().inflate(R.layout.row_patient,viewGroup,false);
        User user = users.get(i);

        TextView paciente_cpf = (TextView) cview.findViewById(R.id.paciente_cpf);
        TextView paciente_nome = (TextView)cview.findViewById(R.id.paciente_nome);
        ImageView imageViewP = (ImageView) cview.findViewById(R.id.img_patient);

        paciente_cpf.setText(user.getCpf());
        paciente_nome.setText(user.getName());
        imageViewP.setImageResource(R.drawable.patient);


        return cview;
    }
}
