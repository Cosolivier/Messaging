package com.coso.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

/**
 * Created by coso on 30/01/2017.
 */
public class Adapter extends ArrayAdapter<Channel>  {
    private final Context context;
    private final List<Channel> values;


    public Adapter(Context context, List<Channel> values) {
        super(context, R.layout.rowchannel, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowchannel, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.textView_name);
        TextView nbco = (TextView) rowView.findViewById(R.id.textView_nbco);
        Channel chan = values.get(position);
        name.setText(chan.getName());
        nbco.setText("nombre d'utilisateurs connectés : " + chan.getConnectedusers().toString());
        return rowView;
    }

}


