package com.comers.kotlin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Comers on 2017/10/14.
 */

public class JavaActivity extends AppCompatActivity {
    private TextView rxExercise;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        rxExercise.setText("java 的世界Q");
    }

    private void initView() {
        rxExercise = (TextView) findViewById(R.id.rxExercise);
    }
}
