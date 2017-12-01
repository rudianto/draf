package com.pamon.app.pamon;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.provider.Browser;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebIconDatabase;

import com.pamon.app.pamon.adapter.AdapterViewPagerLogin;
import com.pamon.app.pamon.session.SessionUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class ActivityLogin extends AppCompatActivity {
    ViewPager viewPager;
    AdapterViewPagerLogin adapterViewPagerLogin;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        adapterViewPagerLogin = new AdapterViewPagerLogin(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapterViewPagerLogin);
        viewPager.setOffscreenPageLimit(3);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("HP ANAK");
        tabLayout.getTabAt(1).setText("HP ORANG TUA");




        findViewById(R.id.labelForgetPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this,ActivityForgetPasword.class));
            }
        });



    }




}
