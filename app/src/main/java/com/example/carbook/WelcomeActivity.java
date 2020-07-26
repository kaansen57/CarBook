package com.example.carbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Intent login = new Intent(getApplicationContext(),Main4Activity.class);
                        startActivity(login);
                        finish();
                        overridePendingTransition(R.anim.page_in,R.anim.page_out);
                    }
                }, 3000);
    }
}
