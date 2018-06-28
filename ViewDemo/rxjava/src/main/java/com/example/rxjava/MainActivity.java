package com.example.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.example.rxjava.event.UserEvent;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RxBus.getInstance().postSticky(1);
        RxBusException.getInstance().post(new UserEvent());
    }


    public void onClick(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }


    CompositeDisposable mCompositeDisposable;


    public void onEvent(Integer integer) {
        mCompositeDisposable = new CompositeDisposable();
        RxBus.getInstance().toObservable(Integer.class).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                mCompositeDisposable.add(d);
            }


            @Override
            public void onNext(Integer integer) {
                Toast.makeText(MainActivity.this, "onNext", Toast.LENGTH_LONG).show();
            }


            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_LONG).show();
            }


            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "onComplete", Toast.LENGTH_LONG).show();
            }
        });
    }
}
