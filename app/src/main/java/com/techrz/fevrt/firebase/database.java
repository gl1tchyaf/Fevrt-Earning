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


public class database extends AppCompatActivity {

    private static int pointgl;
    private static String idgl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

    }

    public void seePoint2(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Employee");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    String id = snapshot1.child("id").getValue(String.class);
                    facebookLogin fb= new facebookLogin();
                    if(id.equals(fb.id)){
                        pointgl = snapshot1.child("point").getValue(int.class);
                        idgl = id;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void saveData(){

        DAOEmployee dao =new DAOEmployee();
        facebookLogin fb = new facebookLogin();
        webview w = new webview();
        Employee emp = new Employee(fb.id, (int)w.point);

        seePoint2();

        System.out.println(fb.id);
        System.out.println(idgl);

        if(fb.id.equals(idgl)){

        }
        else{
            dao.add(emp).addOnSuccessListener(suc ->
            {
                // Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->
            {
                //Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }



    }
}