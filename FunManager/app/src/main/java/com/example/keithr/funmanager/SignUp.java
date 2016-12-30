package com.example.keithr.funmanager;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity {


    //Private user info variables
    String F_Name;
    String L_Name;
    String Username;
    String Password;
    String Fav_Food;
    String Fav_Color;
    String Best_moment;
    int Age;
    private int ID;

    private EditText First_Name;
    private EditText Last_Name;
    private EditText age;
    private EditText Favorite_Food;
    private EditText Favorite_Color;
    private EditText Best_Moment;
    private EditText username;
    private EditText password;

     private Context context;

    public String[] Age_Error_text = {"Error: need age in whole number format"};

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public SignUp() {
    }

    public SignUp(int ID, String F_Name, String L_Name, String Username, String Password, int Age,
                  String Fav_Food, String Fav_Color, String B_moment) {

        this.ID = ID;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.Username = Username;
        this.Password = Password;
        this.Age = Age;
        this.Best_moment = B_moment;
        this.Fav_Color = Fav_Color;
        this.Fav_Food = Fav_Food;
    }

    public SignUp(String F_Name, String L_Name, String Username, String Password, int Age,
                  String Fav_Food, String Fav_Color, String B_moment) {


        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.Username = Username;
        this.Password = Password;
        this.Age = Age;
        this.Fav_Color = Fav_Color;
        this.Fav_Food = Fav_Food;
        this.Best_moment = B_moment;
    }

    public void UserDB(Context cont){
        this.context = cont;
    }

    public String getUserFirstName() {

        return F_Name;
    }

    public String getUserLastName() {

        return L_Name;
    }

    public String getUserName() {

        return Username;
    }

    public String getPassword() {

        return Password;
    }

    public int getAge() {

        return Age;
    }

    public String getFavoritecolor() {

        return Fav_Color;
    }

    public String getFavoriteFood() {

        return Fav_Food;
    }

    public String getBestMoment() {
        return Best_moment;
    }


    public void setUserFirstName() {
        F_Name = First_Name.getText().toString();
    }

    public void setUserLastName() {
        L_Name = Last_Name.getText().toString();
    }

    public void setUserName() {
        Username = username.getText().toString();
    }

    public void setPassword() {
        Password = password.getText().toString();
    }

    public void setAge() {
        try {
            Age = Integer.parseInt(age.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Age must be a Whole Number", Toast.LENGTH_SHORT).show();
        }
    }

    public void setFavoritecolor() {
        Fav_Color = Favorite_Color.getText().toString();
    }

    public void setFavoriteFood() {
        Fav_Food = Favorite_Food.getText().toString();
    }

    public void setBestMoment() {
        Best_moment = Best_Moment.getText().toString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = SignUp.this;
        final Button sButton = (Button) findViewById(R.id.submitbutton);
        //Goes to SignUp Activity

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                First_Name = (EditText) findViewById(R.id.txtfirstname);
                Last_Name = (EditText) findViewById(R.id.txtlastname);
                username = (EditText) findViewById(R.id.txtusername);
                password = (EditText) findViewById(R.id.txtPasswrd);
                age = (EditText) findViewById(R.id.txtAge);
                Favorite_Food = (EditText) findViewById(R.id.txtFavFood);
                Favorite_Color = (EditText) findViewById(R.id.txtFavcolor);
                Best_Moment = (EditText) findViewById(R.id.txtexcitingmoment);
                DBHandler db = new DBHandler(context, null);

                //retreives user text input from screen and put into values
                setUserFirstName();
                setUserLastName();
                setAge();
                setUserName();
                setPassword();
                setFavoritecolor();
                setFavoriteFood();
                setBestMoment();
                //checks Values before entering into database

                if (checkValues()) {
                    db.addNewUser(new SignUp(F_Name, L_Name, Username, Password, Age, Fav_Color, Fav_Food, Best_moment));
                    Toast.makeText(getApplicationContext(), " Congragulations, You are a new User!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, login.class);
                    startActivity(intent);
                }

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //Checks signup data for meeting criteria(min/max number of characters, duplicates, etc.)
    protected boolean checkValues() {
        boolean flag = true;

        if (username.length() < 5 || username.length() > 20) {
            flag = false;
            Toast.makeText(getApplicationContext(), "Enter Username Between 5-20 Characters. ", Toast.LENGTH_SHORT).show();
            //display Username length error message
        }
        if (password.length() < 6 || password.length() > 14) {
            Toast.makeText(getApplicationContext(), "Enter Password Between 6-14 Characters. ", Toast.LENGTH_SHORT).show();
            flag = false;
            //display password length error message on phone screen
        }
        if (username == password) {
            flag = false;
            Toast.makeText(getApplicationContext(), "Username and Password Cannot Match.", Toast.LENGTH_SHORT).show();
            //display username can't be same as password
        }
        return flag;
    }

    public void showError(String error) {

        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SignUp Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.keithr.funmanager/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SignUp Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.keithr.funmanager/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
