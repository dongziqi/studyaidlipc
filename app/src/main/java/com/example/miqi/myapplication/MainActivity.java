package com.example.miqi.myapplication;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.IBinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import com.example.miqi.myapplication.IMyAidlInterface;
import com.example.miqi.myapplication.service.ServiceAidl;

public class MainActivity extends AppCompatActivity {

    private final static  String TAG = "cyj";

    private IMyAidlInterface remote = null;
    private ConnectivityManager mgr = null;
    private int conut = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = findViewById(R.id.bt);
        Button bt2 = findViewById(R.id.bt2);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG,"view onclick...");
                if(remote != null){
                    try {
                        //ServiceManager.getService(Context.CONNECTIVITY_SERVICE);
                       // mgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                        //remote.sayHelloToAC("dongziqi");
                       Helloinfo hi = new Helloinfo("dongziqilala",2);
                        Log.d(TAG, " thread  " + android.os.Process.myTid());
                       remote.syaHelloStoClient(hi);
//                       if(conut < 100){
//                           new Handler(getMainLooper()).postDelayed(new Runnable() {
//                               @Override
//                               public void run() {
//                                   try {
//                                       remote.syaHelloInfo(hi);
//                                   }catch (Exception e){
//                                       Log.d(TAG,".android.os.RemoteException .." + e);
//                                   }
//
//                               }
//                           },5000);
//                       }


//                        Log.d(TAG," MainActivity dongziqilala " + hi.getmHelloname());
//                        Helloinfo hi1 = new Helloinfo("cleint hello",5);
//                        remote.syaHelloStoClient(hi1);
//                        Log.d(TAG," MainActivity syaHelloStoClient " + hi1.getmHelloname());
//                        Helloinfo hi2 = new Helloinfo("double cleint hello",8);
//                        remote.syaHelloDoubleTo(hi2);
////                        Log.d(TAG,"syaHelloDoubleTo " + hi2.getmHelloname());
//                        new Thread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                int i = 1;
//                                while (i < 100) {
//                                    Log.d(TAG, " new thread  " + android.os.Process.myTid());
//                                    try {
//                                        if ((i & 1) != 0) {
//                                            remote.sayHelloTocccc(0);
//                                            Log.d(TAG, "view onclick... 0");
//                                        } else {
//                                            remote.sayHelloTocccc(1);
//                                            Log.d(TAG, "view onclick... 1");
//                                        }
//                                        Thread.sleep(200);
//
//                                    } catch (Exception e) {
//                                        Log.d(TAG, ".android.os.RemoteException .." + e);
//                                    }
//                                    i++;
//                                }
//
//                            }
//                        }).start();

                    } catch (Exception e) {
                        Log.d(TAG, ".android.os.RemoteException .." + e);
                    }

                }

            }
        } );

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "view -----onclick...");

                //必须这样写java.lang.IllegalArgumentException: Service Intent must be explicit:
                // 如果这样写会 爆出什么的错误 Intent serviceintent = new Intent("android.intent.service.ServiceAidl");
                Intent serviceintent = new Intent();
                serviceintent.setAction("android.intent.service.ServiceAidl");
                serviceintent.setPackage(getPackageName());

                bindService(serviceintent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        remote = IMyAidlInterface.Stub.asInterface(iBinder);
                        Log.d(TAG, "remote " + remote);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {
                        remote = null;
                    }
                }, Context.BIND_AUTO_CREATE);


            }

        });




    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
