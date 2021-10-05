package com.techrz.fevrt.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class database extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        final EditText edit_name = findViewById(R.id.edit_name);
        final EditText edit_position = findViewById(R.id.edit_position);
        Button btn = findViewById(R.id.btn_submit);

        DAOEmployee dao =new DAOEmployee();
        Employee emp_edit = (Employee)getIntent().getSerializableExtra("EDIT");

        btn.setOnClickListener(v->
        {
            Employee emp = new Employee(edit_name.getText().toString(), edit_position.getText().toString());
            if(emp_edit==null)
            {
                dao.add(emp).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}