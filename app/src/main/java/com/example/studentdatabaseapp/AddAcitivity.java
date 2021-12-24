package com.example.studentdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddAcitivity extends AppCompatActivity {

    EditText nameStudent, idStudent, ageStudent;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acitivity);

        nameStudent = findViewById(R.id.stdName);
        idStudent = findViewById(R.id.stdid);
        ageStudent = findViewById(R.id.stdAge);

        addBtn = findViewById(R.id.button2);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(AddAcitivity.this);
                db.addStudent(new StudentModel(nameStudent.getText().toString().trim(), Integer.valueOf(ageStudent.getText().toString().trim()),idStudent.getText().toString().trim()));
                finish();
                Intent intent = new Intent(AddAcitivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}