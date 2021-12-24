package com.example.studentdatabaseapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;
    ArrayList<String> student_id;
    ArrayList<String> student_name;
    ArrayList<String> student_age;
    ArrayList<Integer> def_id;
    DatabaseHelper myDB;
    MyCustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recylerView);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(MainActivity.this, AddAcitivity.class);
                startActivity(intent);
            }
        });
        myDB = new DatabaseHelper(MainActivity.this);
        student_id = new ArrayList<>();
        student_name = new ArrayList<>();
        student_age= new ArrayList<>();
        def_id = new ArrayList<Integer>();

        LoadDataFromDb();

        customAdapter = new MyCustomAdapter(MainActivity.this,this, student_id, student_name,
                student_age);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    private void LoadDataFromDb() {
        Cursor cursor = myDB.readAllData();
        int i=0;
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data in db", Toast.LENGTH_LONG).show();

        }else{
            while (cursor.moveToNext()){
                i++;
                student_id.add(cursor.getString(0));
                student_name.add(cursor.getString(1));
                student_age.add(cursor.getString(2));
                def_id.add(i);
            }
        }
    }
}