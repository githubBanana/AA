package com.xs.aa;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-06-01 18:24
 * @email Xs.lin@foxmail.com
 */
public class NotifyActivity extends AppCompatActivity {

    private final String TAG = NotifyActivity.class.getSimpleName();
    private LinearLayout mView;

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(TAG);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = (LinearLayout) findViewById(R.id.parentId);
        final TextView mTv = (TextView) findViewById(R.id.tv_show_version);
        RxView.clicks(mTv).subscribe(aVoid -> notifyTest4());
    }

    /**
     * 普通通知
     */
    private void notifyTest() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotifyActivity.this);
        // 设置通知的基本信息：icon、标题、内容
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("My notification");
        builder.setContentText("Hello World!");
        builder.setAutoCancel(true);

// 设置通知的点击行为：这里启动一个 Activity
        Intent intent = new Intent(this, ResultActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(NotifyActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

// 发送通知 id 需要在应用内唯一
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    /**
     * 浮动通知
     */
    private void notifyTest2() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotifyActivity.this);

        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);
        builder.setContentTitle("wo shi title");
        builder.setContentText("text");
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
// 设置通知的提示音
        builder.setSound(alarmSound);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setProgress(100,50,false);
        Intent intent = new Intent(this,ResultActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(NotifyActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2,builder.build());

    }

    /**
     * 样式扩展通知
     */
    private void notifyTest3() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotifyActivity.this);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("nofity3");
        builder.setContentText("notify_test");

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle(builder);
        inboxStyle.setBigContentTitle("扩展样式:");
        for (int i = 0; i < 10; i++) {
            inboxStyle.addLine("line"+i);
        }
        builder.setStyle(inboxStyle);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(3,builder.build());
    }
    public static final String KEY_TEXT_REPLY = "key_text_reply";

    /**
     * android N 可回复通知
     */
    private void notifyTest4() {
        // Key for the string that's delivered in the action's intent
        String replyLabel = "输入您想说的...";
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        Intent intent = new Intent(this,ResultActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(NotifyActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

// Create the reply action and add the remote input
        Notification.Action action = new Notification.Action.Builder(R.mipmap.ic_launcher,
                "请回复",pendingIntent)
                .addRemoteInput(new android.app.RemoteInput.Builder(KEY_TEXT_REPLY).setLabel(replyLabel).build())
                .build();

        // Build the notification and add the action
        Notification notification = new Notification.Builder(NotifyActivity.this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentTitle("rr")
                .setContentText("22")
                .setAutoCancel(true)
                .addAction(action)
                .build();


// Issue the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(4,notification);


    }
}

