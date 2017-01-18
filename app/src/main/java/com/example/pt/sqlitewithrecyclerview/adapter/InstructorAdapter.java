package com.example.pt.sqlitewithrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pt.sqlitewithrecyclerview.R;
import com.example.pt.sqlitewithrecyclerview.model.Instructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pt on 5/4/16.
 */
public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.MyViewHolder> {

    private List<Instructor> instructorList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email, salary,dept ,id;
        public Button edit;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.id);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            salary = (TextView) view.findViewById(R.id.salary);
            dept = (TextView) view.findViewById(R.id.dept);




        }
    }


    public InstructorAdapter(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_of_instructor, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Instructor instructor = instructorList.get(position);
        holder.id.setText(instructor.getId());
        holder.name.setText(instructor.getName());
        holder.email.setText(instructor.getEmail());
        holder.dept.setText(instructor.getDept_name());
        holder.salary.setText(String.valueOf(instructor.getSalary()));

    }

    public void setFilter(List<Instructor> instructor) {
        instructorList = new ArrayList<>();
        instructorList.addAll(instructor);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return instructorList.size();
    }
}