package com.techrz.fevrt.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class database extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

    }
    public void saveData(){

        DAOEmployee dao =new DAOEmployee();
        facebookLogin fb = new facebookLogin();
        webview w = new webview();
        Employee emp = new Employee(fb.id, (int)w.point);

            dao.add(emp).addOnSuccessListener(suc ->
            {
               // Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->
            {
                //Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });

    }
}