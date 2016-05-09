package com.eric.imitate.icsdn.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2015/11/27.
 */
public class NetUtils {
    public static boolean checkNet(Context context){
        boolean wifiConnected = isWIFIConnected(context);
        boolean liuliangConnected = isLIULIANGConnected(context);
        if(wifiConnected == false && liuliangConnected == false){
            return false;
        }
        return true;
    }

    private static boolean isLIULIANGConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    private static boolean isWIFIConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }


}
