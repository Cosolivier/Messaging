package com.coso.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.InterfaceAddress;
import java.util.HashMap;

public class ChannelListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,OnDownloadCompleteListener {

    private ListView lvChannels;

    public static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel);

        lvChannels = (ListView) findViewById(R.id.listView_channel);
        lvChannels.setOnItemClickListener(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String accestoken = settings.getString("accesstoken","");

        HashMap<String,String> params = new HashMap<>();
        params.put("accesstoken",accestoken);
        Connexion d = new Connexion(this,params,"http://www.raphaelbischof.fr/messaging/?function=getchannels");
        d.setOnDownloadCompleteListener(this);
        d.execute();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent myIntent = new Intent(getApplicationContext(),ChatView.class);
        startActivity(myIntent);
    }



    @Override
    public void onDownloadCompleted(String result) {
        Gson gson = new Gson();
        Channels c = gson.fromJson(result, Channels.class);

        for(Channel ch :c.channels){
            Toast.makeText(this, ch.name, Toast.LENGTH_SHORT).show();
        }

        lvChannels = (ListView) findViewById(R.id.listView_channel);
        lvChannels.setAdapter(new Adapter(getApplicationContext(), c.channels));
        lvChannels.setOnItemClickListener(this);
    }


}
