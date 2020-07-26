package com.example.carbook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

public class Main2Activity extends AppCompatActivity {
    ArrayList<CarModelClass> liste;
    ListView lstv;
    String HttpUrl = "http://192.168.1.103:8080/carbook/carmodel.php?carid=";
    RequestQueue requestQueue;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.page_in,R.anim.page_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Model Listesi");
        lstv = findViewById(R.id.listview);
        Bundle extras = getIntent().getExtras();
        int value = extras.getInt("carid") + 1;//carid position

     //   Toast.makeText(this, String.valueOf(value), Toast.LENGTH_SHORT).show();
        liste = new  ArrayList<CarModelClass>();
        RequestQueue istek = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =
                new StringRequest(Request.Method.GET,
                        HttpUrl+value, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsArray = new JSONArray(response.toString());
                            for (int i=0;i<jsArray.length();i++)
                            {
                                JSONObject jsobj = jsArray.getJSONObject(i);
                                liste.add(new CarModelClass(jsobj.get("photo").toString(),
                                                jsobj.get("carmodelname").toString(),
                                                jsobj.get("carmodeldesc").toString()));
                            }
                        }
                        catch (JSONException e) {
                            Toast.makeText(Main2Activity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                        ModelAdapter adapter = new ModelAdapter(getApplicationContext(), R.layout.customlayout,liste);
                        lstv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main2Activity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        istek.add(stringRequest);
    }
}