package com.xs.aa;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-06-02 11:31
 * @email Xs.lin@foxmail.com
 */
public class MuiltiActiviry extends AppCompatActivity {
    private static final String TAG = "BroadCastActiviry";
    private final String ACTION_TEST = "test";
    private LinearLayout mView ;

    private int process = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = (LinearLayout) findViewById(R.id.parentId);
        final TextView mTv = (TextView) findViewById(R.id.tv_show_version);
        RxView.clicks(mTv).subscribe(aVoid -> {
         /*   Intent intent = new Intent(ACTION_TEST);
            LocalBroadcastManager.getInstance(BroadCastActiviry.this).sendBroadcast(intent);*/
            Intent intent = new Intent(MuiltiActiviry.this,NotifyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);
            startActivity(intent);
        });

        Log.e(TAG, "onCreate:isInMultiWindowMode: "+isInMultiWindowMode() );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " );
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        Log.e(TAG, "onMultiWindowModeChanged: "+isInMultiWindowMode );
    }
}
