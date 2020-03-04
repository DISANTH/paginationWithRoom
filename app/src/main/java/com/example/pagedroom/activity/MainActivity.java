package com.example.pagedroom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pagedroom.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Button button = findViewById(R.id.button);
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReportActivity();
            }
        } );
    }
    public void showReportActivity()
    {
        Intent intent = new Intent(this,DailyReportActivity.class);
        startActivity(intent);
    }
}
