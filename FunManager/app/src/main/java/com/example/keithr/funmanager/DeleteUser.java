package com.example.keithr.funmanager;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteUser extends AppCompatActivity {

    private String username;
    private String password;
    private String favfood;
    private int id;

    private EditText Username;
    private EditText Password;
    private EditText FavFood;

    DBHandler Deldb;

    public DeleteUser(){
    }

    public DeleteUser(String userName, String PassWord, String favFood){
        this.favfood = favFood;
        this.username = userName;
        this.password = PassWord;
    }

    public DeleteUser(int ID, String userName, String PassWord, String favFood){
        this.id = ID;
        this.favfood = favFood;
        this.username = userName;
        this.password = PassWord;
    }

    public void setUsernameDel(){
        username = Username.getText().toString();
    }

    public void setPasswordDel(){
        password = Password.getText().toString();
    }

    public void setFavFoodDel(){
        favfood = FavFood.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        final Button sButton = (Button) findViewById(R.id.btndeleteacct);
        //Verifies user cridentials before deleting acct

        assert sButton != null;

        sButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Username = (EditText) findViewById(R.id.txtUsername);
                Password = (EditText) findViewById(R.id.txtPassword);
                FavFood = (EditText) findViewById(R.id.txtVerification);

                //assign values as string text
                setUsernameDel();
                setPasswordDel();
                setFavFoodDel();

                try {
                    Deldb.DeleteUser(new DeleteUser(username, password, favfood));

                }
                catch (SQLException e)
                {
                    Toast.makeText(getApplicationContext(), "Could not delete User from Database", Toast.LENGTH_SHORT).show();
                }
                finally {

                    Toast.makeText(getApplicationContext(), "User Deleted.", Toast.LENGTH_SHORT).show();
                }
            }


        });


    }
}
