package com.comers.successing;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.comers.baselibrary.utils.ToastUtils;
import com.comers.baselibrary.utils.UIUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class HomeActivity extends AppCompatActivity {

    private TextView rxExercise;
    private TextView txHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Example of a call to a native method
        initView();
        initListener();
        HandlerThread thread=new HandlerThread("jsjdf");
        thread.start();
        Looper.prepare();
        Handler handler=new Handler(Looper.getMainLooper());
        ToastUtils.showToast("hhhhhhhh");
        UIUtils.INSTANCE.getVersionCode(this);
        Handler handler6=new Handler();
        Handler handler1=new Handler(Looper.getMainLooper());
        Handler handler2=new Handler(Looper.myLooper());
        Queue queue=new ArrayDeque();
        queue.poll();
        queue.peek();
    }

    private void initListener() {
        rxExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext("hahshsha");
                        e.onNext("hahshsha----");
                        e.onNext("hahshsha----000000");
                        e.onNext("hahshsha----00000000000");
                        e.onComplete();
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("haha",s);
                    }
                });
                List list=new ArrayList();
                list.add("fasafadfadf11");
                list.add("fasajdf55j55dj55555");
                list.add("fasa rghsdf");
                list.add("fasa rghsdf");
                list.add("fassdfadf22222");
                list.add("fassdfadf22222");
                list.add("fasdfjdjdj666666666666");
                list.add("fasdf333jj3333");
               Observable.fromIterable(list).concatMap(new Function<String, ObservableSource<String>>() {
                   @Override
                   public ObservableSource<String> apply(String s) throws Exception {
                       return Observable.just(s);
                   }
               }).filter(new Predicate<String>() {
                   @Override
                   public boolean test(String str) throws Exception {
                       return str.length()>7;
                   }
               }).distinct().subscribe(new Consumer<String>() {
                   @Override
                   public void accept(String string) throws Exception {
                       Log.i("haha",string);
                   }
               });

            }
        });
       /* txHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,KotlinActivity.class);
                startActivity(intent);
            }
        });*/
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private void initView() {
        rxExercise = findViewById(R.id.rxExercise);
        txHello = findViewById(R.id.sample_text);
        txHello.setText(stringFromJNI());
    }
}
