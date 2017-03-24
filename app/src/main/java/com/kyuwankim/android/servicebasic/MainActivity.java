package com.kyuwankim.android.servicebasic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button btn_start, btn_stop, bind, unbind, call;
    MyService bService;
    boolean isService = false;

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder mb = (MyService.MyBinder) service;
            bService = mb.getService();
            isService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isService = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        bind = (Button) findViewById(R.id.bindservice);
        unbind = (Button) findViewById(R.id.unbindservice);
        call = (Button) findViewById(R.id.btncall);

        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
        call.setOnClickListener(this);


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_start:


                intent = new Intent(this, MyService.class);
                startService(intent);


                break;

            case R.id.btn_stop:


                intent = new Intent(this, MyService.class);
                stopService(intent);

                break;

            case R.id.bindservice:
                intent = new Intent(this, MyService.class);
                bindService(intent, conn, Context.BIND_AUTO_CREATE);

                break;

            case R.id.unbindservice:

                break;

            case R.id.btncall:
                bService.print("Main에서 넘겨준 Message");
                break;

        }
    }
}
