package com.example.miqi.myapplication;


import android.os.Parcel;
import android.os.Parcelable;

public class Helloinfo implements Parcelable {

    private  String mHelloname = "miqi";

    public String getmHelloname() {
        return mHelloname;
    }

    public void setmHelloname(String mHelloname) {
        this.mHelloname = mHelloname;
    }

    public void setMindex(int mindex) {
        this.mindex = mindex;
    }

    public int getMindex() {
        return mindex;
    }

    public Helloinfo(){
        this.mHelloname = "";
        this.mindex = 0;
    }
    public Helloinfo(String mHelloname, int mindex) {
        this.mHelloname = mHelloname;
        this.mindex = mindex;
    }

    private  int mindex = 11;
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public  void writeToParcel(Parcel dest, int flags){
        synchronized (this){
            dest.writeString(mHelloname);
            dest.writeInt(mindex);
        }
    }


    public void readFromParcel(Parcel src){
        synchronized (this){
            mHelloname = src.readString();
            mindex = src.readInt();

        }
    }

    public static final  Creator<Helloinfo> CREATOR = new Creator<Helloinfo>() {
        @Override
        public Helloinfo createFromParcel(Parcel in){
            String helloinfoname = in.readString();
            int index = in.readInt();
            return new Helloinfo(helloinfoname,index);
        }

        @Override
        public Helloinfo[] newArray(int size){
            return  new Helloinfo[size];
        }
    };




}
