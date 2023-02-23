package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ArrayList<String> favouriteMovies = new ArrayList<>();
    private FavouriteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        setTitle("Favourites");

        ListView favouriteList = findViewById(R.id.favouriteList);

        dbHelper = new DatabaseHelper(this);
        Cursor result = dbHelper.getFavouriteMovies();

        //check whether the cursor has data and adding them to array list
        if (result.getCount() == 0){
            Toast.makeText(this,"No Entry exists", Toast.LENGTH_SHORT).show();
        }else {
            while (result.moveToNext()){
                favouriteMovies.add(result.getString(0));
            }
        }

        adapter = new FavouriteListAdapter(this, R.layout.listview_row, favouriteMovies);
        favouriteList.setAdapter(adapter);

    }

    //when user clicks save this method executes
    public void clickSave(View view){
        ArrayList<String> deselectedFavourites = adapter.getDeselectedFavourites();

        Boolean checkUpdateData = false;
        for(String item: deselectedFavourites){
            dbHelper.updateFavourite(item);
            checkUpdateData = dbHelper.updateFavourite(item);
        }
        //check whether if the data has been updated
        if (checkUpdateData){
            Toast.makeText(this," Entry Updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this," Entry Not Updated", Toast.LENGTH_SHORT).show();
        }
    }
}