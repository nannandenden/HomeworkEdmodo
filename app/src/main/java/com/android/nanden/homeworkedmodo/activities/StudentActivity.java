package com.android.nanden.homeworkedmodo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.nanden.homeworkedmodo.R;
import com.android.nanden.homeworkedmodo.model.Student;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentActivity extends AppCompatActivity {

    private Student student;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.ivAvatar) ImageView ivAvatar;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvSubmitDate) TextView tvSubmitDate;
    @BindView(R.id.tvContent) TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        ButterKnife.bind(this);

        setView();

    }

    private void setView() {
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra(getString(R.string.intent_title));
        if (!title.isEmpty()) {
            getSupportActionBar().setTitle(title);
        } else {
            getSupportActionBar().setTitle(getString(R.string.app_name));
        }

        student = getIntent().getParcelableExtra(getString(R.string.intent_student));

        ivAvatar.setImageResource(0);
        Picasso.with(this).load(student.getAvatar()).into(ivAvatar);

        tvName.setText(student.getName());
        String submitDate = "turned in " + student.getSubmitDate();
        tvSubmitDate.setText(submitDate);
        tvContent.setText(student.getContent());
    }
}
