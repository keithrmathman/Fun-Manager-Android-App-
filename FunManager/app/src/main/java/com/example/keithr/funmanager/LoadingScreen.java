package com.example.keithr.funmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.Thread;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        java.lang.Thread myThread = new Thread()
        {
        @Override
        public void run(){
            try {
                sleep(3000);
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        };
        myThread.start();
    }
}
