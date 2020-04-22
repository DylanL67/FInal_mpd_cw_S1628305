package com.gcu.mpd.mpd_cw_s1628305;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

/**
 *  * Name: Dylan Lewis
 *  * Student ID: S1628305
 *  *
 *  * @author Dylan
 *  * @version 1.0
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<String> RoadIncidentsLinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button RoadIncidentbtn1 = findViewById(R.id.RoadIncidentbtn1);
        RoadIncidentbtn1.setOnClickListener(this);

        Button RoadIncidentbtn2 = findViewById(R.id.RoadIncidentbtn2);
        RoadIncidentbtn2.setOnClickListener(this);

        Button exitbutton = findViewById(R.id.exitbutton);
        exitbutton.setOnClickListener(this);

        RoadIncidentsLinks.add("https://trafficscotland.org/rss/feeds/plannedroadworks.aspx");
        RoadIncidentsLinks.add("https://trafficscotland.org/rss/feeds/roadworks.aspx");
        RoadIncidentsLinks.add("https://trafficscotland.org/rss/feeds/currentincidents.aspx");
    }

    @Override
    public void onClick(View view){
        Intent trfcsctlnd = new Intent(getApplicationContext(), RSSFeedActivity.class);
        switch(view.getId()){
            case R.id.RoadIncidentbtn1:
                trfcsctlnd.putExtra("rssLink", RoadIncidentsLinks.get(0));
                startActivity(trfcsctlnd);
                break;
            case R.id.RoadIncidentbtn2:
                trfcsctlnd.putExtra("rssLink", RoadIncidentsLinks.get(1));
                startActivity(trfcsctlnd);
                break;
            case R.id.exitbutton:
                showtbDialog();
                break;
        }
    }

    private void showtbDialog(){
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage("Exit app?");
        bld.setCancelable(false);
        bld.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "Goodbye", Toast.LENGTH_SHORT).show();
                MainActivity.this.finish();
            }
        });
        bld.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getApplicationContext(), "Continuing use", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        AlertDialog alert = bld.create();
        alert.show();
    }
}