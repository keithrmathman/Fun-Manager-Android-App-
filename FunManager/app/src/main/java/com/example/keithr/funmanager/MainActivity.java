package com.example.keithr.funmanager;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button SubmitButton =(Button) findViewById(R.id.LogOut);
        TabHost tabhost = (TabHost) findViewById(R.id.tabHost);

        tabhost.setup();

        TabHost.TabSpec tabSpec = tabhost.newTabSpec("Main Menu");
        tabSpec.setContent(R.id.tabmainmenu);
        tabSpec.setIndicator("Main Menu");
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec("User Content");
        tabSpec.setContent(R.id.tabUserInfo);
        tabSpec.setIndicator("User Content");
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec("View Profile");
        tabSpec.setContent(R.id.tabViewProfile);
        tabSpec.setIndicator("View Profile");
        tabhost.addTab(tabSpec);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void storeImage(int ID, Bitmap Img) {

        byte[] data =  getBitmapAsByteArray(Img); // this is a function
       // insertStatement_logo.bindLong(1, ID);
      //  insertStatement_logo.bindBlob(2, data);

      //  insertStatement_logo.executeInsert();
        //insertStatement_logo.clearBindings();

    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
