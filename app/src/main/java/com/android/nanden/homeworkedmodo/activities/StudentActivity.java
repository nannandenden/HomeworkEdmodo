package com.android.nanden.homeworkedmodo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.nanden.homeworkedmodo.R;
import com.android.nanden.homeworkedmodo.model.Student;
import com.squareup.picasso.Picasso;

public class StudentActivity extends AppCompatActivity {

    private Student student;
    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvSubmitDate;
    private TextView tvContent;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        setView();

    }

    private void setView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);

        student = getIntent().getParcelableExtra("student");

        ivAvatar = (ImageView) findViewById(R.id.ivAvatar);
        ivAvatar.setImageResource(0);
        Picasso.with(this).load(student.getAvatar()).into(ivAvatar);

        tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(student.getName());

        tvSubmitDate = (TextView) findViewById(R.id.tvSubmitDate);
        tvSubmitDate.setText(student.getSubmitDate());

        tvContent = (TextView) findViewById(R.id.tvContent);
        tvContent.setText(student.getContent());
    }
}
