package com.xs.aa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.jakewharton.rxbinding.view.RxView;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String  TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView mTv = (TextView) findViewById(R.id.tv_show_version);
      /*  mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUser();
            }
        });*/
//        RxView.clicks(mTv).subscribe(aVoid -> handleUser());
        RxView.clicks(mTv).observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> Toast.makeText(MainActivity.this,Thread.currentThread().getName(),Toast.LENGTH_LONG).show());
    }


    public Observable<User> getUser() {
        Observable observable =  Observable.create(new Observable.OnSubscribe<List<User>>() {
            @Override
            public void call(Subscriber<? super List<User>> subscriber) {
                List<User> list = new ArrayList<User>();
                boolean f = false;
                for (int i = 0; i < 3; i++) {
                    f = !f;
                    User user = new User();
                    user.setName("name"+i);
                    user.setSex(i);
                    user.setGuest(f);
                    list.add(user);
                }
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        });
        User user1 = new User(); user1.setGuest(false);user1.setSex(1);user1.setName("lin 1");
        User user2 = new User(); user2.setGuest(true); user2.setSex(2);user2.setName("lin 2");
        Observable ob = Observable.just(user1,user2);
        return ob;
    }

    public void handleUser() {
        getUser().filter(new Func1<User, Boolean>() {
            @Override
            public Boolean call(User user) {
                return user.isGuest();
            }
        })
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG,"onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError:"+e );
                    }

                    @Override
                    public void onNext(User user) {
                        Log.e(TAG, "onNext333: "+user.toString());
                    }
                });
    }
}
