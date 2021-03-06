package com.example.student.study;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;


public class ServiceActivity extends ActionBarActivity {

   private EditText mEditStopCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service
        );

        mEditStopCount = (EditText) findViewById(R.id.edit_stop_count);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this,StartService.class);
                intent.putExtra(StartService.EXTRA_STOP_TIME,mEditStopCount.getText().toString());
                startService(intent);
            }
        });
    }

}
