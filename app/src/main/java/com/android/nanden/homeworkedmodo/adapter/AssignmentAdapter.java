package com.android.nanden.homeworkedmodo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.nanden.homeworkedmodo.R;
import com.android.nanden.homeworkedmodo.model.Assignment;

import java.util.List;

/**
 * Created by nanden on 9/19/17.
 */

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    public interface onItemClickListener {
        void onItemClick(Assignment assignment);
    }
    private Context context;
    private final List<Assignment> assignments;

    private final onItemClickListener listener;

    public AssignmentAdapter(Context context, List<Assignment> assignments, onItemClickListener
            listener) {
        this.context = context;
        this.assignments = assignments;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.content_homework, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Assignment assignment = this.assignments.get(position);
        holder.bind(assignment, listener);
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDueDate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvAssignmentTitle);
            tvDueDate = itemView.findViewById(R.id.tvDueDate);
        }

        public void bind(final Assignment assignment, final onItemClickListener listener) {
            tvTitle.setText(assignment.getTitle());
            tvDueDate.setText(assignment.getDueDate());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(assignment);
                }
            });
        }
    }
}