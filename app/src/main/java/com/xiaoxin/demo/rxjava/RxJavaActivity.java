package com.xiaoxin.demo.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.trello.rxlifecycle2.components.RxActivity;
import com.xiaoxin.demo.R;
import com.xiaoxin.demo.login.ui.LoginActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends RxActivity {

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_test)
    Button mBtnTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {

        useRxBindingMethod1();

        useRxBindingMethod2();
    }


    @SuppressLint("CheckResult")
    private void useRxBindingMethod2() {
        RxTextView.textChanges(mEtUsername)
                //当EditText停止改变1秒后执行操作
                .debounce(1000, TimeUnit.MILLISECONDS)
                //下面这两个都是数据转换
                //flatMap：当同时多个网络请求访问的时候，前面的网络数据会覆盖后面的网络数据
                //switchMap：当同时多个网络请求访问的时候，会以最后一个发送请求为准，前面网络数据会被最后一个覆盖
                .switchMap(new Function<CharSequence, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(CharSequence charSequence) throws Exception {
                        //被观察者Observable将数据发送出去
                        return Observable.just("2429");
                    }
                })
                .delay(2400, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mEtPassword.setText(s);//接收数据
                    }
                });
    }


    @SuppressLint("CheckResult")
    private void useRxBindingMethod1() {
        //给button设置点击事件
        RxView.clicks(mBtnTest)
                //两秒内只截取第一个事件
                .throttleFirst(2, TimeUnit.SECONDS)
                //延迟两秒执行
                .throttleWithTimeout(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                //如果事件消费里面有UI更新，一定要指定在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mEtUsername.setText("RxAndroid");
                        mEtPassword.setText("RxBinding");
                    }
                });
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        LoginActivity.start(this,LoginActivity.class);
//        useRxJavaMethod1(username, password);
//        useRxJavaMethod2(username, password);
//        useRxJavaMethod3(username);

    }


    @SuppressLint("CheckResult")
    private void useRxJavaMethod3(final String username) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //被观察者Observable执行的线程取决于subscribeOn指定的线程
                Log.d("tag", Thread.currentThread().getName());
                emitter.onNext(username + "yll");
                emitter.onComplete();
            }
        }).delay(2400, TimeUnit.MILLISECONDS)
                //绑定到当前activity的生命周期上，activity销毁则请求停止，
                // 防止activity销毁请求还在继续，会导致内存泄漏
                //一般用于网络请求或流的读写等耗时操作
                .compose(this.<String>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //接收被观察者的发送数据
                        Log.d("tag", "doOnNext.s = " + s);
                        mEtPassword.setText(s);
                    }
                })
                //将doOnNext中接收的数据继续进行转换
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        //map里面执行的操作在主线程
                        return s + "cc24";
                    }
                })
                .delay(2900, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("tag", s);
                        mEtUsername.setText(s);
                    }
                });
    }

    private void useRxJavaMethod2(final String username, final String password) {
        //创建被观察者Observable
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //emitter为发射器，连续发射几个数据，在调用onComplete时则不会继续发送数据，
                // onComplete和onError只能同时调用一个
                emitter.onNext(username + "青承丹蕾");
                emitter.onNext(password + "青承丹蕾");
                emitter.onNext(username + password + "青承丹蕾");
                emitter.onComplete();
            }
        }).delay(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //事件订阅，观察者接收数据
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Disposable可以理解为控制器，d.dispose()之后观察者Observer则不再接收数据
                        //但是被观察者Observable可以继续发送数据
                    }

                    @Override
                    public void onNext(String s) {
                        //接收到被观察者Observable发送的数据，即emitter.onNext的数据
                        //D/tag: cc青承丹蕾
                        //D/tag: yll青承丹蕾
                        //D/tag: ccyll青承丹蕾
                        Log.d("tag", s);
                        mEtPassword.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @SuppressLint("CheckResult")
    private void useRxJavaMethod1(String username, String password) {
        //创建被观察者Observable
        //just可以发送一个或多个数据
        Observable.just(username, password)
                //延迟两秒发送
                .delay(2000, TimeUnit.MILLISECONDS)
                //map方法表示把传入的参数转换之后返回另一个对象
                //function的第一个参数为传入的值，而且是just方法最后一个参数的值，第二个参数为要转换的对象
                //apply参数即为传入的值,return的即为转换完成的值
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + "24";//此时结果为password+24，覆盖了username的值
                    }
                })
                //如果连续map操作，则是将上一个map转换完成的结果继续在下个map作为参数进行转换
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + "29";//此时结果为password+24+29
                    }
                })
                //事件发生的线程，一般操作为访问网络，流的读写等
                .subscribeOn(Schedulers.io())
                //事件消费的线程，一般为更新UI
                .observeOn(AndroidSchedulers.mainThread())
                //在执行的过程中
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mBtnLogin.setText("请稍候...");
                    }
                })
                //事件订阅,成功和失败的方法，总共有6种实现方式
                //Consumer即相当于onNext方法
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //accept方法参数的值就是map方法里转换完成的值,也就是apply方法return的值
                        mEtUsername.setText(s);
                        mEtPassword.setText(s);
                        mBtnLogin.setText("登录");
                    }
                });
    }
}
