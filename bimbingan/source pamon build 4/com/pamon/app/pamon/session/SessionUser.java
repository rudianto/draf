package com.pamon.app.pamon.session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sofiyanudin on 04/11/2017.
 */

public class SessionUser {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefsEditor;

    final static int TYPE_USER_A = 0;
    final static int TYPE_USER_B = 1;

    public SessionUser(Context context){
        sharedPreferences = context.getSharedPreferences("SessionUser", Context.MODE_PRIVATE);


    }

    public void login(int idUser, String username, int typeUser){
        prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt("idUser", idUser);
        prefsEditor.putString("username", username);
        prefsEditor.putInt("typeUser", typeUser);
        prefsEditor.commit();
    }

    public int getIdUser(){
        if (sharedPreferences!= null) {
            return sharedPreferences.getInt("idUser", 0);
        }
        return 0;
    }

    public String getUsername(){
        if (sharedPreferences!= null) {
            return sharedPreferences.getString("username", null);
        }
        return null;
    }
    public int getTypeUser(){
        if (sharedPreferences!= null) {
            return sharedPreferences.getInt("typeUser", 0);
        }
        return 0;
    }



    public String getUserAccess(){
        if(getTypeUser() == 0)
            return "(Driver)";
        else
            return "(Orang Tua)";
    }
    public boolean isLogin(){
        if(getIdUser() == 0)
            return false;
        else
            return true;
    }
    public void logout(){
        prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt("idUser", 0);
        prefsEditor.commit();
    }
}
