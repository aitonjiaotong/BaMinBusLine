package com.jiaotong.aiton.aiton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputLocActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mInput_cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_loc);
        initUI();
        setListener();
    }

    private void setListener() {
        mInput_cancle.setOnClickListener(this);
    }

    private void initUI() {
        mInput_cancle = (TextView) findViewById(R.id.input_cancle);
        EditText intput_edit = (EditText) findViewById(R.id.input_edit);
        Intent intent = getIntent();
        String intputType = intent.getStringExtra("inputType");
        if ("myLoc".equals(intputType)){
            intput_edit.setHint("输入起点");
        }else if("end".equals(intputType)){
            intput_edit.setHint("输入终点");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.input_cancle:
                finish();
                break;
        }
    }
}
