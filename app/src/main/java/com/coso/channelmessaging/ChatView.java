package com.coso.channelmessaging;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by coso on 06/02/2017.
 */
public class ChatView extends Activity implements OnDownloadCompleteListener, AdapterView.OnItemClickListener {


    private ListView ChatContent;

    public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatview);
        ChatContent = (ListView) findViewById(R.id.listView_message);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String accestoken = settings.getString("accesstoken","");

        HashMap<String,String> params = new HashMap<>();
        params.put("accesstoken",accestoken);
        Connexion d = new Connexion(this,params,"http://www.raphaelbischof.fr/messaging/?function=getmessages");
        d.setOnDownloadCompleteListener(this);
        d.execute();


    }

    @Override
    public void onDownloadCompleted(String content) {
        Gson gson = new Gson();
        Channels c = gson.fromJson(content, Channels.class);

        for(Channel ch :c.channels){
            Toast.makeText(this, ch.name, Toast.LENGTH_SHORT).show();
        }

        ChatContent = (ListView) findViewById(R.id.listView_channel);
        ChatContent.setAdapter(new Adapter(getApplicationContext(), c.channels));
        ChatContent.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

