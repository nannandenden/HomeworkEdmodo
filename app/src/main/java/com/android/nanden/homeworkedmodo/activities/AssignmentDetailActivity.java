package com.android.nanden.homeworkedmodo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.nanden.homeworkedmodo.R;
import com.android.nanden.homeworkedmodo.Utils;
import com.android.nanden.homeworkedmodo.adapter.AssignmentDetailAdapter;
import com.android.nanden.homeworkedmodo.client.StudentClient;
import com.android.nanden.homeworkedmodo.model.Assignment;
import com.android.nanden.homeworkedmodo.model.Student;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AssignmentDetailActivity extends AppCompatActivity implements AssignmentDetailAdapter.OnItemClickListener{

    private static final String LOG_TAG = AssignmentDetailActivity.class.getSimpleName();

    private Assignment assignment;
    private List<Student> students;
    private AssignmentDetailAdapter adapter;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.rvStudentDetail) RecyclerView rvStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);
        ButterKnife.bind(this);
        setView();
        setStudentsList();
    }

    private void setView() {
        assignment = getIntent().getParcelableExtra(getString(R.string
                .intent_assignment));

        setSupportActionBar(toolbar);
        if (!assignment.getTitle().isEmpty()) {
            getSupportActionBar().setTitle(assignment.getTitle());
        } else {
            getSupportActionBar().setTitle(getString(R.string.app_name));
        }

        if (!assignment.getDescription().isEmpty()) {
            tvDescription.setText(assignment.getDescription());
        } else {
            tvDescription.setText(R.string.no_description);
        }

        students = new ArrayList<>();
        adapter = new AssignmentDetailAdapter(this, students, this);
        rvStudents.setAdapter(adapter);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
    }


    private void setStudentsList() {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(LOG_TAG, "Network call error: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                JSONArray jsonArray;
                try {
                    jsonArray = new JSONArray(responseData);
                    students.addAll(Student.fromJsonArray(jsonArray));

                } catch (JSONException e) {
                    Log.d(LOG_TAG, e.getMessage());
                }

                AssignmentDetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        };

        if (Utils.isNetworkAvailable(this)) {
            StudentClient client = new StudentClient();
            String id = Integer.toString(assignment.getId());
            client.getStudents(id, callback);
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(Student student) {
        Intent intent = new Intent(AssignmentDetailActivity.this, StudentActivity.class);
        intent.putExtra(getString(R.string.intent_student), student);
        intent.putExtra(getString(R.string.intent_title), assignment.getTitle());
        startActivity(intent);
    }
}
