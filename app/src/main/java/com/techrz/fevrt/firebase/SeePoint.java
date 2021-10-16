package com.techrz.fevrt.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SeePoint extends AppCompatActivity {

    public static int pointGlob;
    public static String idglob;
    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_point);

        seePoint();


    }

    public void seePoint(){
        listview = findViewById(R.id.listview);

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listview.setAdapter(adapter);

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Employee");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    String id = snapshot1.child("id").getValue(String.class);
                    String points = snapshot1.child("point").getValue(int.class).toString();
                    facebookLogin fb= new facebookLogin();
                    if(id.equals(fb.id)){
                        list.add(points);
                        pointGlob = snapshot1.child("point").getValue(int.class);
                        idglob = id;

                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}