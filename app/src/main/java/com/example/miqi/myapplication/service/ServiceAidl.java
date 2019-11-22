package com.example.miqi.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.miqi.myapplication.IMyAidlInterface;

public class ServiceAidl extends Service {
    private final  static  String TAG = "dzq";
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent){
        return  new MyAidlInterfaceImp();
    }

    private static class MyAidlInterfaceImp extends IMyAidlInterface.Stub{

        public void sayHelloToAC(String name) {
            Log.d(TAG,"service sayHelloToAC " + name);
        }

    }

}
