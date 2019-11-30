// IMyAidlInterface.aidl
package com.example.miqi.myapplication;

// Declare any non-default types here with import statements

import com.example.miqi.myapplication.Helloinfo;

    interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    //void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
          //  double aDouble, String aString);

    void sayHelloToAC(String name);
    void syaHelloInfo(in Helloinfo hi);
    void syaHelloStoClient(out Helloinfo hi);
    //void syaHelloDoubleTo(inout Helloinfo hi2);


}
