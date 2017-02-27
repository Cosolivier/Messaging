package com.coso.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by coso on 06/02/2017.
 */
public class ChatAdapter  extends ArrayAdapter<Message> {
    private final Context context;
    private final List<Message> values;


    public ChatAdapter(Context context, List<Message> values) {
        super(context, R.layout.rowchat, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowchat, parent, false);
        TextView userId = (TextView) rowView.findViewById(R.id.textView_userId);
        TextView message = (TextView) rowView.findViewById(R.id.textView_messageContent);
        TextView date = (TextView) rowView.findViewById(R.id.textView_dateMessage);


        Message mess = values.get(position);
        userId.setText(mess.getUserID());
        message.setText("Message : " + mess.getMessage().toString());
        date.setText("Message : " + mess.getMessage().toString());

        return rowView;
    }

}