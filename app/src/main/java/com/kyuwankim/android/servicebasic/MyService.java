package com.kyuwankim.android.servicebasic;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = "MyService";


    IBinder mbinder = new MyBinder();


    class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mbinder;
    }

    public void print(String value){

        System.out.println("Service value ============== " + value);
    }








    public MyService() {
    }







    @Override
    public void onCreate() {
        System.out.println("OnCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("OnDestroy");

        super.onDestroy();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println("OnStartCommand");

        for (int i = 0; i < 10; i++) {
            Toast.makeText(getApplicationContext(), "서비스 동작중" + i, Toast.LENGTH_SHORT).show();

        }
        return super.onStartCommand(intent, flags, startId);
    }



}
