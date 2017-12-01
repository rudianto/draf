package com.pamon.app.pamon;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.pamon.app.pamon.helper.HelperLocation;
import com.pamon.app.pamon.helper.HelperMan;
import com.pamon.app.pamon.model.ResponseStatusCode;
import com.pamon.app.pamon.network.ApiNetwork;
import com.pamon.app.pamon.session.SessionUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ActivityAnak extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anak);


        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SessionUser(ActivityAnak.this).logout();
                Intent i = new Intent(ActivityAnak.this,ActivityMain.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
                finish();
            }
        });

       getBookmarksHistory();
        sendLocation();
    }

    public boolean state_send = false;
    public void sendLocation(){
        HelperLocation helperLocation = new HelperLocation(ActivityAnak.this);
        helperLocation.getLocation(new HelperLocation.OnResponseLocation() {
            @Override
            public void onLocationChanged(Location location) {
                if(location != null){

                    if(state_send == false){
                        state_send = true;
                        ApiNetwork apiNetwork = new ApiNetwork(ActivityAnak.this);
                        apiNetwork.putLokasi(new SessionUser(ActivityAnak.this).getIdUser(),
                                location.getLatitude(), location.getLongitude(), new ApiNetwork.OnReponseStatusCode() {
                                    @Override
                                    public void onResponse(ResponseStatusCode statusCode) {
                                        state_send =false;
                                    }

                                    @Override
                                    public void onFailure() {
                                        state_send =false;
                                    }
                                });
                    }


                }
            }
        });
    }
    private void getKontakString(){


        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... voids) {
                JSONArray jsonArray = null;

                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.Contacts.HAS_PHONE_NUMBER;
                Cursor cursor = getApplicationContext().getContentResolver().query(uri, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME}, selection, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");


                try {
                    jsonArray = new JSONArray();

                    while (cursor.moveToNext()) {
                        String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("NAME", HelperMan.urlEncoder(contactName));
                        jsonObject.put("PHONE", HelperMan.urlEncoder(contactNumber));

                        jsonArray.put(jsonObject);
                        cursor.moveToNext();
                    }

                    cursor.close();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(jsonArray != null)
                    return jsonArray.toString();
                else
                    return null;

            }

            @Override
            protected void onPostExecute(String s) {
                ApiNetwork apiNetwork = new ApiNetwork(ActivityAnak.this);
                apiNetwork.putKontak(new SessionUser(ActivityAnak.this).getIdUser(), s, new ApiNetwork.OnReponseStatusCode() {
                    @Override
                    public void onResponse(ResponseStatusCode statusCode) {

                    }

                    @Override
                    public void onFailure() {

                    }
                });
                super.onPostExecute(s);


            }
        }.execute();


    }


    public void getBookmarksHistory(){
        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... voids) {
                JSONArray jsonArray = null;

                ContentResolver cr = getContentResolver();
                String CONTENT_URI = "content://com.android.chrome.browser/history";
                Uri URI = Uri.parse(CONTENT_URI);
                Cursor cursor = null;
                try {
                    jsonArray = new JSONArray();
                    cursor = cr.query(URI, new String[]{"url"}, null,
                            null, null);
                    if (cursor != null) {
                        while (cursor.moveToNext()){

                            String url = cursor.getString(cursor.getColumnIndex("url"));
                            JSONObject jsonObject = new JSONObject();

                            jsonObject.put("URL", HelperMan.urlEncoder(url));
                            jsonArray.put(jsonObject);
                            cursor.moveToNext();
                        }
                        cursor.close();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    if (cursor != null) cursor.close();
                }

                if(jsonArray != null)
                    return jsonArray.toString();
                else return null;
            }

            @Override
            protected void onPostExecute(String s) {

                getKontakString();
                ApiNetwork apiNetwork = new ApiNetwork(ActivityAnak.this);
                apiNetwork.putHistory(new SessionUser(ActivityAnak.this).getIdUser(), s, new ApiNetwork.OnReponseStatusCode() {
                    @Override
                    public void onResponse(ResponseStatusCode statusCode) {

                    }

                    @Override
                    public void onFailure() {

                    }
                });

                super.onPostExecute(s);
            }
        }.execute();


    }



}
