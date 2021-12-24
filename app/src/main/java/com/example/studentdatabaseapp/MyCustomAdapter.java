package com.example.studentdatabaseapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyView> {

    private Context context;
    private Activity activity;
    private ArrayList student_id, student_name, student_age;

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
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.student_id.setText(String.valueOf(student_id.get(position)));
        holder.student_name.setText(String.valueOf(student_name.get(position)));
        holder.student_age.setText("Age" + String.valueOf(student_age.get(position)));

    }

    @Override
    public int getItemCount() {
        return student_id.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView student_id, student_name, student_age;

        public MyView(@NonNull View itemView) {
            super(itemView);
            student_name = itemView.findViewById(R.id.student_name_txt);
            student_age = itemView.findViewById(R.id.student_age);
            student_id = itemView.findViewById(R.id.student_roll_no);
        }
    }
}
