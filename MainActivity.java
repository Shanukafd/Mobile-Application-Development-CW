package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchRegisterActivity(View view){
        Intent intent = new Intent(this, RegisterActivity.class);  //launches Register Activity from the home screen
        startActivity(intent);
    }

    public void launchDisplayActivity(View view){
        Intent intent = new Intent(this, DisplayActivity.class);  //launches Display Activity from the home screen
        startActivity(intent);
    }

    public void launchFavouriteActivity(View view){
        Intent intent = new Intent(this, FavouriteActivity.class);  //launches Favourite Activity from the home screen
        startActivity(intent);
    }

    public void launchEditActivity(View view){
        Intent intent = new Intent(this, EditActivity.class);  //launches Edit Activity from the home screen
        startActivity(intent);
    }

    public void launchSearchActivity(View view){
        Intent intent = new Intent(this, SearchActivity.class);  //launches Search Activity from the home screen
        startActivity(intent);
    }


}