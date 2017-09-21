package com.android.nanden.homeworkedmodo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.nanden.homeworkedmodo.R;
import com.android.nanden.homeworkedmodo.Utils;
import com.android.nanden.homeworkedmodo.adapter.AssignmentAdapter;
import com.android.nanden.homeworkedmodo.client.AssignmentClient;
import com.android.nanden.homeworkedmodo.model.Assignment;

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

public class AssignmentsActivity extends AppCompatActivity implements AssignmentAdapter
        .onItemClickListener {

    private static final String LOG_TAG = AssignmentsActivity.class.getSimpleName();

    private List<Assignment> assignments;
    private AssignmentAdapter adapter;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rvAssignments) RecyclerView rvAssignments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        ButterKnife.bind(this);

        setView();
        setAssignmentList();

    }

    private void setView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.assignment));
        assignments = new ArrayList<>();
        adapter = new AssignmentAdapter(this, assignments, this);
        rvAssignments.setAdapter(adapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setAssignmentList() {
        Callback callback = new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                JSONArray jsonArray;

                try {
                    jsonArray = new JSONArray(responseData);
                    assignments.addAll(Assignment.fromJsonArray(jsonArray));

                } catch (JSONException e) {
                    Log.d(LOG_TAG, "Network call error: " + e.getMessage());
                }

                AssignmentsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        };

        if (Utils.isNetworkAvailable(this)) {
            AssignmentClient client = new AssignmentClient();
            client.getAssignments(callback);
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(Assignment assignment) {
        Intent intent = new Intent(AssignmentsActivity.this, AssignmentDetailActivity.class);
        intent.putExtra(getString(R.string.intent_assignment), assignment);
        startActivity(intent);
    }
}
