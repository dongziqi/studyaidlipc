package com.example.miqi.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class HardwareInfo {
    private static final String TAG = "UserEnvironment";
    private static final String FAKE_MAC_ADDRESS = "02:00:00:00:00:00";
    private static final String DEFAULT_MAC_ADDRESS = "0";

    @SuppressWarnings({"MissingPermission"})
    public static String getWifiMacAddress(Context context) {
        String macAddress = getWifiMacAddressHack();
        if (!TextUtils.isEmpty(macAddress) && !macAddress.equals(DEFAULT_MAC_ADDRESS)){
            return macAddress;
        }

        if (context == null) {
            return DEFAULT_MAC_ADDRESS;
        }
        try {
            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (wm.getConnectionInfo() == null) {
                return DEFAULT_MAC_ADDRESS;
            }
            macAddress = wm.getConnectionInfo().getMacAddress();
            if(!TextUtils.isEmpty(macAddress) && !macAddress.equals(FAKE_MAC_ADDRESS)){
                return macAddress;
            }
        } catch (SecurityException e) {
            Log.e(TAG, "failed to get Mac Address", e);
        }

        return DEFAULT_MAC_ADDRESS;
    }

    private static String getWifiMacAddressHack() {
        byte[] mac = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null){
                return DEFAULT_MAC_ADDRESS;
            }

            while (networkInterfaces.hasMoreElements()){
                NetworkInterface intf = networkInterfaces.nextElement();
                if (intf.getName() != null && intf.getName().equalsIgnoreCase("wlan0")){
                    mac = intf.getHardwareAddress();
                    break;
                }
            }
        } catch (SocketException ex) {
            Log.e(TAG, "failed to get wifi Mac Address", ex);
            return DEFAULT_MAC_ADDRESS;
        }

        if (mac == null) {
            return DEFAULT_MAC_ADDRESS;
        }

        StringBuilder buf = new StringBuilder();
        for (byte aMac : mac) {
            buf.append(String.format("%02X:", aMac));
        }
        if (buf.length()>0) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

}
