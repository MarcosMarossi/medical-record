package com.example.emr.Config;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.emr.R;

public class Holder extends RecyclerView.ViewHolder {
    public TextView dateLog, bpmLog;


    public Holder(@NonNull View itemView) {
        super(itemView);
        this.bpmLog = itemView.findViewById(R.id.BPM_Log);
        this.dateLog = itemView.findViewById(R.id.Date_Log);
    }
}
