package com.example.studentdatabaseapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, id_input, age_input;
    Button update_button, delete_button;
    String id, age, name, title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.stdName1);
        id_input = findViewById(R.id.stdid1);
        age_input = findViewById(R.id.stdAge1);

        update_button = findViewById(R.id.button2);
        delete_button = findViewById(R.id.button3);


        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(id);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                id = id_input.getText().toString().trim();
                age = age_input.getText().toString().trim();
                myDB.updateData(getIntent().getStringExtra("std_id"), id, name, age);
                finish();
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("std_name") && getIntent().hasExtra("std_name") &&
                getIntent().hasExtra("age")){
            //Getting Data from Intent
            name = getIntent().getStringExtra("std_name");
            id = getIntent().getStringExtra("std_id");
            age = getIntent().getStringExtra("age");

            //Setting Intent Data
            name_input.setText(name);
            id_input.setText(id);
            age_input.setText(age);
            Log.d("stev", name+" "+id+" "+age);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void DeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " id Student ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}