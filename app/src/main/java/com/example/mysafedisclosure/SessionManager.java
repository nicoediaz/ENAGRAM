package com.example.mysafedisclosure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE=0;

    private static final String PREF_NAME ="LOGIN";
    private static final String LOGIN="IS_LOGIN";
    public static final String USER_NAME="USER_NAME";
    public static final String ID="ID";


    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences= context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=sharedPreferences.edit();
    }

    public void createSession(String username, String id){
        editor.putBoolean(LOGIN, true);
        editor.putString(USER_NAME,username);
        editor.putString(ID,id);
        editor.apply();
    }

    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public  void checkLoggin(){
        if(!this.isLoggin()){
            Intent i = new Intent(context,LoginActivity.class);
            context.startActivity(i);
            ((PostActivity) context).finish();
        }
    }

    public HashMap<String,String> getUserDetail() {
        HashMap<String, String> user= new HashMap<>();
        user.put(USER_NAME, sharedPreferences.getString(USER_NAME,null));
        user.put(ID,sharedPreferences.getString(ID,null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context,LoginActivity.class);
        context.startActivity(i);
        ((PostActivity) context).finish();
    }
}
