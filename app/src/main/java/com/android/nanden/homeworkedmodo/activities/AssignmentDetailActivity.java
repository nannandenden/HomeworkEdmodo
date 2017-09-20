package com.android.nanden.homeworkedmodo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AssignmentDetailActivity extends AppCompatActivity implements AssignmentDetailAdapter.OnItemClickListener{
    private static final String LOG_TAG = AssignmentDetailActivity.class.getSimpleName();
    private Assignment assignment;
    private TextView tvDescription;
    private RecyclerView rvStudents;
    private List<Student> students;
    private AssignmentDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assignment = getIntent().getParcelableExtra(getString(R.string
                .intent_assignment));
        getSupportActionBar().setTitle(assignment.getTitle());
        setView();
        setStudentList();
    }

    private void setView() {
        tvDescription = (TextView) findViewById(R.id.tvDetail);
        if (!assignment.getDescription().isEmpty()) {
            tvDescription.setText(assignment.getDescription());
        }
        rvStudents = (RecyclerView) findViewById(R.id.rvDetail);
        students = new ArrayList<>();
        adapter = new AssignmentDetailAdapter(this, students, this);
        rvStudents.setAdapter(adapter);
        rvStudents.setLayoutManager(new LinearLayoutManager(this));
    }


    private void setStudentList() {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(LOG_TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(LOG_TAG, response.toString());
                String responseData = response.body().string();
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(responseData);
                    students.addAll(Student.fromJsonArray(jsonArray));
                    Log.d(LOG_TAG, students.toString());

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
        }
    }

    @Override
    public void onItemClick(Student student) {
        
    }
}
