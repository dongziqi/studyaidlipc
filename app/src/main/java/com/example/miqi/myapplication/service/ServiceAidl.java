package com.example.miqi.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.miqi.myapplication.IMyAidlInterface;
import com.example.miqi.myapplication.Helloinfo;

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

        public  void syaHelloInfo(Helloinfo hi){

                Log.d(TAG,"service Helloinfo name" + hi.getmHelloname());
                Log.d(TAG,"service Helloinfo index" + hi.getMindex());
                hi.setmHelloname("service hello");

       }

        public  void syaHelloStoClient(Helloinfo hi){

            Log.d(TAG,"syaHelloStoClient Helloinfo name " + hi.getmHelloname());
            Log.d(TAG,"syaHelloStoClient Helloinfo index " + hi.getMindex());
            hi.setmHelloname("syaHelloStoClient hello");
            hi.setMindex(3);



        }
//        public  void syaHelloDoubleTo(Helloinfo hi){
//
//            Log.d(TAG,"syaHelloDoubleTo Helloinfo name" + hi.getmHelloname());
//            Log.d(TAG,"syaHelloDoubleTo Helloinfo index" + hi.getMindex());
//            hi.setmHelloname("syaHelloDoubleTo  hello");
//            hi.setMindex(4);
//        }

    }

}
