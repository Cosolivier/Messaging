package com.coso.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private EditText id;
    private EditText mdp;
    private Button btnValider;

    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnValider = (Button) findViewById(R.id.button_valider);
        id = (EditText) findViewById(R.id.editText_identifiant);
        mdp = (EditText) findViewById(R.id.editText_mdp);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username",id.getText().toString());
                params.put("password",mdp.getText().toString());

                Connexion connexion = new Connexion(getApplicationContext(), params, "http://www.raphaelbischof.fr/messaging/?function=connect");

                connexion.setOnDownloadCompleteListener(new  OnDownloadCompleteListener() {
                    @Override
                    public void onDownloadCompleted(String result) {
                        //déserialisation
                        Gson gson = new Gson();
                        Reponse obj = gson.fromJson(result, Reponse.class);

                        if(obj.getResponse().equals("Ok")){
                            Toast.makeText(getApplicationContext(), "Connecté", Toast.LENGTH_SHORT).show();

                            //Shared preferences
                            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("accesstoken", obj.getAccesstoken());
                            // Commit the edits!
                            editor.commit();

                            Intent myIntent = new Intent(LoginActivity.this,ChannelListActivity.class);
                            startActivity(myIntent);


                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Erreur de connexion", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                connexion.execute();
                Toast.makeText(getApplicationContext(), "Connexion en cours...", Toast.LENGTH_SHORT).show();

            }
        });
    }
}