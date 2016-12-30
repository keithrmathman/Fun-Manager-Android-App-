package com.example.keithr.funmanager;

/**
 * Created by Keith r on 12/12/2016.
 */
public class UserSignUp {

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

    public UserSignUp() {

    }

    public UserSignUp(int ID, String F_Name, String L_Name, String Username, String Password, int Age,
                      String Fav_Food, String Fav_Color, String Best_moment) {

        this.ID = ID;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.Username = Username;
        this.Password = Password;
        this.Age = Age;
        this.Best_moment = Best_moment;
        this.Fav_Color = Fav_Color;
        this.Fav_Food = Fav_Food;
    }

    public UserSignUp(String F_Name, String L_Name, String Username, String Password, int Age,
                      String Fav_Food, String Fav_Color, String Best_moment) {


        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.Username = Username;
        this.Password = Password;
        this.Age = Age;
        this.Best_moment = Best_moment;
        this.Fav_Color = Fav_Color;
        this.Fav_Food = Fav_Food;
    }

}