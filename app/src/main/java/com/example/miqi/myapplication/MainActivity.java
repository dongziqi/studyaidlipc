package com.example.miqi.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.example.miqi.myapplication.service.ServiceAidl;

public class MainActivity extends AppCompatActivity {

    private final static  String TAG = "dzq";

    private IMyAidlInterface remote = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG,"view onclick...");
                if(remote != null){
                    try {
                        remote.sayHelloToAC("dongziqi");

                    }catch (android.os.RemoteException e){
                        Log.d(TAG,".android.os.RemoteException .." + e);
                    }

                }

            }
        } );

        //必须这样写java.lang.IllegalArgumentException: Service Intent must be explicit:
        // 如果这样写会 爆出什么的错误 Intent serviceintent = new Intent("android.intent.service.ServiceAidl");
        Intent serviceintent = new Intent();
        serviceintent.setAction("android.intent.service.ServiceAidl");
        serviceintent.setPackage(getPackageName());

        bindService(serviceintent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                remote =  IMyAidlInterface.Stub.asInterface(iBinder);
                Log.d(TAG,"remote " + remote);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                remote = null;
            }
        }, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
