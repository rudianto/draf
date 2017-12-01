package com.pamon.app.pamon.helper;

import android.content.Context;
import android.location.LocationManager;
import android.support.v7.app.AlertDialog;
import android.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by Sofiyanudin on 05/11/2017.
 */

public class HelperMan {
    public static void message(String s, Context cIs){
        AlertDialog.Builder alert = new AlertDialog.Builder(cIs);
        alert.setMessage(s);
        alert.show();
    }

    public static String urlEncoder(String u){
        String s = "";
        try {
            s = URLEncoder.encode(u,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String urlDecoder(String u){
        String s = "";
        try {
            s = URLDecoder.decode(u,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static String decodeBase64(String s){

        byte[] decrypt= Base64.decode(s, Base64.NO_PADDING);
        try {
            return new String(decrypt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "UnsupportedEncodingException";
        } catch (IllegalArgumentException e){
            return "IllegalArgumentException";
        }catch (IOException e){
            return "IOException";
        }


    }
    public static boolean checkGPS(Context context){
        final LocationManager manager = (LocationManager)context.getSystemService    (Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) )
            return  false;
        else
            return  true;
    }
}
