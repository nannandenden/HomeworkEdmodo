package com.android.nanden.homeworkedmodo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.nanden.homeworkedmodo.R;
import com.android.nanden.homeworkedmodo.model.Student;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * AssignmentDetailAdapter-
 */

public class AssignmentDetailAdapter extends RecyclerView.Adapter<AssignmentDetailAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Student student);
    }
    private final Context context;
    private final List<Student> students;
    private final OnItemClickListener listener;

    public AssignmentDetailAdapter(Context context, List<Student> students, OnItemClickListener
            listener) {
        this.context = context;
        this.students = students;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_assignment_detail,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = students.get(position);
        holder.bind(student, listener);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvSubmitDate) TextView tvSubmitDate;
        @BindView(R.id.ivAvatar) ImageView ivAvatar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Student student, final OnItemClickListener listener) {
            tvName.setText(student.getName());
            String submitDate = "turned in " + student.getSubmitDate();
            tvSubmitDate.setText(submitDate);
            ivAvatar.setImageResource(0);
            Picasso.with(context).load(student.getAvatar()).into(ivAvatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(student);
                }
            });

        }
    }
}
