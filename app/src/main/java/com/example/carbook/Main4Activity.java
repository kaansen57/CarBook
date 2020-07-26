package com.example.carbook;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class Main4Activity extends AppCompatActivity {
    EditText username, password;
    Button loginButton;
    RequestQueue requestQueue;
    String emailHolder, passwordHolder;
    ProgressDialog progressDialog;
    String HttpUrl = "http://192.168.1.103:8080/carbook/login.php";
    Boolean editTextCheck;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        txt = findViewById(R.id.textView);
        username = findViewById(R.id.editText_Email);
        password =  findViewById(R.id.editText_Password);
        loginButton = findViewById(R.id.button_login);

        inputAnimation();
        progressDialog = new ProgressDialog(Main4Activity.this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textAlanKontrol();
                if (editTextCheck) {
                    UserLogin();
                } else {
                    showWarningToast();
                }
            }
        });
    }
    public void UserLogin() {
        progressDialog.setMessage("Lütfen Bekleyiniz.");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        progressDialog.dismiss();

                        if(ServerResponse.equalsIgnoreCase("success")) {
                            showWelcomeToast(username.getText().toString());
                            finish();
                            Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.page_in,R.anim.page_out);
                        } else {
                            showErrorToast();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(Main4Activity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", emailHolder);
                params.put("password", passwordHolder);
                return params;
            }
        };
            requestQueue = Volley.newRequestQueue(Main4Activity.this);
            requestQueue.add(stringRequest);
    }
    public void textAlanKontrol() {
        emailHolder = username.getText().toString().trim();
        passwordHolder = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailHolder) || TextUtils.isEmpty(passwordHolder)) {
            editTextCheck = false;
        } else {
            editTextCheck = true;
        }
    }
    private void showWelcomeToast(String username){
        ViewGroup welcomeLayout = findViewById(R.id.welcome_layout);
        LayoutInflater inflater = getLayoutInflater();
        View view  = inflater.inflate(R.layout.custom_toast_welcome ,welcomeLayout);
        TextView text =  view.findViewById(R.id.welcomeTxt);
        text.setText(username + "  Hoşgeldiniz");
        text.startAnimation(AnimationUtils.loadAnimation(this,R.anim.zoomin));
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
    }
    private void showErrorToast() {
        LayoutInflater inflater = getLayoutInflater();
        View view  = inflater.inflate(R.layout.custom_toast,null);
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();

    }
    private void showWarningToast() {
        username.startAnimation(AnimationUtils.loadAnimation(this,R.anim.shake));
        password.startAnimation(AnimationUtils.loadAnimation(this,R.anim.shake));
        LayoutInflater inflater = getLayoutInflater();
        View view  = inflater.inflate(R.layout.custom_toast_warning,null);
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();

    }
    private void inputAnimation() {
        txt.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bounce));
        username.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bounce));
        password.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bounce));
        loginButton.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bounce));
    }
}

