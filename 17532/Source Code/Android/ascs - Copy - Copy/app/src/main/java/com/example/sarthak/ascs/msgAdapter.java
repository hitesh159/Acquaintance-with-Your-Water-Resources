package com.example.sarthak.ascs;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sarthak on 3/30/2018.
 */

public class msgAdapter extends BaseAdapter {
    Context context;
    List<ChatMessage> list;

    public msgAdapter(Context context, List<ChatMessage> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();

    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi=View.inflate(context,R.layout.msglist,null);
        TextView left=vi.findViewById(R.id.leftText);
        TextView right=vi.findViewById(R.id.rightText);
        if (list.get(i).getMsgUser()=="user")
        {
            right.setText(list.get(i).getMsgText());
            left.setVisibility(View.INVISIBLE);
        }
        else {
            left.setText(list.get(i).getMsgText());
            right.setVisibility(View.INVISIBLE);

        }
        return vi;
    }
}
