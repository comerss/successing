package com.comers.rxjava;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.Subject;

public class HomeActivity extends AppCompatActivity {

    private Button rxFlatMap;
    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initListener();
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        final HandlerThread thread = new HandlerThread("我是一个HandlerThread");
        thread.start();
        Handler handler = new Handler(thread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //处理异步任务
                Log.i("renwu", "-----------------接受到一个异步任务---------" + thread.getLooper().getThread().getName());
                Toast.makeText(HomeActivity.this, "fahdf", Toast.LENGTH_SHORT).show();
                rxFlatMap.setText("我是一个按钮");
                return false;
            }
        });
        handler.sendEmptyMessage(555);

    }

    private void initListener() {
        rxFlatMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HandlerThread thread = new HandlerThread("我是一个HandlerThread");
                thread.start();
                Handler handler = new Handler(thread.getLooper(), new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        //处理异步任务
                        Log.i("renwu", "-----------------接受到一个异步任务---------" + thread.getLooper().getThread().getName());
                        Toast.makeText(HomeActivity.this, "fahdf", Toast.LENGTH_SHORT).show();
                        rxFlatMap.setText("我是一个按钮");
                        return false;
                    }
                });
                handler.sendEmptyMessage(555);


                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {

                    }
                }).subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {

                    }
                }).subscribe(new Subject<String>() {
                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    protected void subscribeActual(Observer<? super String> observer) {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                List<String> list = new ArrayList<>();
                Observable.fromIterable(list).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });
                Observable.interval(30, TimeUnit.MILLISECONDS)
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {

                            }
                        });
                Observable.just("").map(new Function<String, Object>() {
                    @Override
                    public Object apply(String s) throws Exception {
                        return null;
                    }
                }).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });
            }
        });
    }

    private void initView() {
        rxFlatMap = (Button) findViewById(R.id.rxFlatMap);
    }
}
