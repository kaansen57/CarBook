package com.example.carbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ArrayList<carClass> liste;
    ListView lstv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Marka Listesi");
        lstv = findViewById(R.id.listview);
        lstv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomin));
        liste = new  ArrayList<carClass>();
        RequestQueue istek = Volley.newRequestQueue(getApplicationContext());
        /* !!!! ip adresini ipconfig -> ipv4 bak ve değiştir.*/
        String adres = "http://192.168.1.103:8080/carbook/servis.php?carGet";
        StringRequest stringRequest =
                new StringRequest(Request.Method.GET,
                        adres, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsArray = new JSONArray(response.toString());
                            for (int i=0;i<jsArray.length();i++)
                            {
                                JSONObject jsobj = jsArray.getJSONObject(i);
                                liste.add(new carClass(jsobj.get("photo").toString(),
                                        jsobj.get("carname").toString(),
                                        jsobj.get("cardescription").toString())); }
                        }
                        catch (JSONException e) {
                            Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                        Customadapter adapter = new Customadapter(getApplicationContext(), R.layout.customlayout,liste);
                        lstv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show(); }
                });
        istek.add(stringRequest);
     lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.together));
                new Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                Intent aciklama = new Intent(getApplicationContext(),Main2Activity.class);
                                aciklama.putExtra("carid", i);
                                startActivity(aciklama);
                                overridePendingTransition(R.anim.page_in,R.anim.page_out); }
                                }, 1000); }
       });
    }
}

