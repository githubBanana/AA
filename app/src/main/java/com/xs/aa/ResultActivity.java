package com.xs.aa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-06-03 15:12
 * @email Xs.lin@foxmail.com
 */
public class ResultActivity extends AppCompatActivity {
    private static final String TAG = "ResultActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_layout);
        final TextView mTv = (TextView) findViewById(R.id.tv_result);
        String text = getIntentData(getIntent());
        mTv.setText(text);
    }

    private String getIntentData(Intent intent) {
        Bundle bundle = RemoteInput.getResultsFromIntent(intent);
        String text = (String) bundle.get(NotifyActivity.KEY_TEXT_REPLY);
        return text;
    }

}
