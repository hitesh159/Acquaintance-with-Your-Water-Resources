package com.example.sarthak.ascs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Sarthak on 3/30/2018.
 */

public class chat_rec extends RecyclerView.ViewHolder {
    TextView leftText,rightText;

    public chat_rec(View itemView){
        super(itemView);

        leftText = (TextView)itemView.findViewById(R.id.leftText);
        rightText = (TextView)itemView.findViewById(R.id.rightText);


    }
}
