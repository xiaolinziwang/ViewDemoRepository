package com.example.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.rxjava.event.UserEvent;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;

public class Main2Activity extends AppCompatActivity {

    private CompositeDisposable mCompositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RxBusException.getInstance().toObservable(UserEvent.class)
        .subscribe(new Action<UserEvent>());
        //mCompositeDisposable = new CompositeDisposable();
        //RxBus.getInstance().toObservable(Integer.class)
        //     .subscribe(new Observer<Integer>() {
        //         @Override
        //         public void onSubscribe(Disposable d) {
        //             mCompositeDisposable.add(d);
        //         }
        //
        //
        //         @Override
        //         public void onNext(Integer integer) {
        //             Toast.makeText(Main2Activity.this,
        //                     "onNext",Toast.LENGTH_LONG).show();
        //         }
        //
        //
        //         @Override
        //         public void onError(Throwable e) {
        //             Toast.makeText(Main2Activity.this,
        //                     "onError",Toast.LENGTH_LONG).show();
        //         }
        //
        //
        //         @Override
        //         public void onComplete() {
        //             Toast.makeText(Main2Activity.this,
        //                     "onComplete",Toast.LENGTH_LONG).show();
        //         }
        //     });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //RxBus.rxBusUnband(mCompositeDisposable);
    }
}
