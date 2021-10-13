package com.techrz.fevrt.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class home extends AppCompatActivity {
    static String link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        facebookLogin login = new facebookLogin();
        TextView name= findViewById(R.id.profilename);
        ImageView profilephoto = findViewById(R.id.profilePic);
        Button logout = findViewById(R.id.logout);
        Button point = findViewById(R.id.seePoint);

        name.setText(login.name);
        Picasso.get().load("https://graph.facebook.com/"+login.id+"/picture?type=large").into(profilephoto);

        ImageView facebook= findViewById(R.id.facebook);
        ImageView youtube= findViewById(R.id.youtube);
        ImageView google= findViewById(R.id.google);


        facebook.setOnClickListener(v -> facebook());
        youtube.setOnClickListener(v -> youtube());
        google.setOnClickListener(v -> google());

        logout.setOnClickListener(v -> logout());

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seePoint();
            }
        });


    }

    void seePoint(){
        Intent i = new Intent(home.this,SeePoint.class);
        startActivity(i);
    }

    void facebook(){
        link ="https:www.facebook.com";
        Intent i=new Intent(home.this,webview.class);
        startActivity(i);
    }
    void youtube(){
        link ="https:www.youtube.com";
        Intent i=new Intent(home.this,webview.class);
        startActivity(i);
    }
    void google(){
        link ="https:www.google.com";
        Intent i=new Intent(home.this,webview.class);
        startActivity(i);
    }

    void logout(){
        Intent intent = new Intent(home.this,facebookLogin.class);
        startActivity(intent);
    }

    @Override
    public void onStop(){
        super.onStop();
        finish();

    }

    @Override
    public void onBackPressed() {

    }
}