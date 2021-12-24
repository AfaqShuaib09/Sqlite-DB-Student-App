package com.example.studentdatabaseapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyView> {

    private Context context;
    private Activity activity;
    private ArrayList student_id, student_name, student_age;
    Animation anim;

    MyCustomAdapter(Activity activity, Context context, ArrayList student_id, ArrayList student_name,
                    ArrayList student_age){
        this.activity = activity;
        this.context = context;
        this.student_id =student_id;
        this.student_name = student_name;
        this.student_age = student_age;
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, @SuppressLint("RecyclerView") int position) {
        holder.student_id.setText(String.valueOf(student_id.get(position)));
        holder.student_name.setText(String.valueOf(student_name.get(position)));
        holder.student_age.setText("Age" + String.valueOf(student_age.get(position)));
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("std_name", String.valueOf(student_name.get(position)));
                intent.putExtra("std_id", String.valueOf(student_id.get(position)));
                intent.putExtra("age", String.valueOf(student_age.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return student_id.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView student_id, student_name, student_age;
        LinearLayout mainlayout;

        public MyView(@NonNull View itemView) {
            super(itemView);
            student_name = itemView.findViewById(R.id.student_name_txt);
            student_age = itemView.findViewById(R.id.student_age);
            student_id = itemView.findViewById(R.id.student_roll_no);
            mainlayout = itemView.findViewById(R.id.mainLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.anim);
            mainlayout.setAnimation(translate_anim);

        }
    }
}
