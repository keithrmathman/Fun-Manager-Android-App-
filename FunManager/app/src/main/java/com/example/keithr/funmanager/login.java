package com.example.keithr.funmanager;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    boolean LoggedIn;

    String Username;
    String Password;

    private EditText username;
    private EditText password;

    private Context context;


    public login(){}

    public login(String Username, String Password){
        this.Username = Username;
        this.Password = Password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button sButton = (Button) findViewById(R.id.signupbutton);
       //Goes to SignUp Activity
        assert sButton != null;
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(login.this, SignUp.class);
                startActivity(intent);
            }

        });

        final Button lButton = (Button) findViewById(R.id.loginbutton);
        assert lButton != null;
        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = (EditText) findViewById(R.id.editText);
                password = (EditText) findViewById(R.id.editText2);

                DBHandler db = new DBHandler(context,null);
                setLoginCredentials();

                try {
                  LoggedIn = db.LoginUser(new login(Username, Password));

                }
                catch (SQLException e)
                {
                    Toast.makeText(getApplicationContext(), "Could not Login", Toast.LENGTH_SHORT).show();
                }
                finally {

                    if(LoggedIn == true){
                        Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    }

                    else
                        Toast.makeText(getApplicationContext(), "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                }




            }

        });





    }

    public void setLoginCredentials(){

        Username = username.getText().toString();
        Password = password.getText().toString();
    }
}
