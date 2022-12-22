package com.example.myapplication.read;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Constant;
import com.example.myapplication.R;

public class Cases_ShowActivity extends AppCompatActivity {
    private TextView tvName, tvDesc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getIntentMain();
    }

    private void init()
    {
        tvName = findViewById(R.id.case_name);
        tvDesc = findViewById(R.id.case_desc);
    }
    private void getIntentMain()
    {
        Intent i = getIntent();
        if(i != null)
        {
            tvName.setText(i.getStringExtra(Constant.CASE_NAME));
            tvDesc.setText(i.getStringExtra(Constant.CASE_DESCRIPTION));
        }
    }
}
