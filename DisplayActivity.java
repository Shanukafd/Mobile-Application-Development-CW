package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    private ArrayList<String> watchedMovies = new ArrayList<>();
    private ArrayList<String> favouriteMovies = new ArrayList<>();

    private DatabaseHelper dbHelper;
    private MovieListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        setTitle("Watched Movies");

        ListView movieListView = findViewById(R.id.movieListView);


        dbHelper = new DatabaseHelper(this);
        Cursor result = dbHelper.getMovieTitle();
        Cursor result2 = dbHelper.getFavouriteMovies();


        //check whether if returned cursor has values and adding them to arraylist
        if (result.getCount() == 0 && result2.getCount() == 0 ){
            Toast.makeText(this,"No Entry exists", Toast.LENGTH_SHORT).show();
        }else {
            while (result.moveToNext()){
                watchedMovies.add(result.getString(0));
            }
            while (result2.moveToNext()){
                favouriteMovies.add(result2.getString(0));
            }
        }


        adapter = new MovieListAdapter(this, R.layout.listview_row, watchedMovies,favouriteMovies); //passing parameters

        movieListView.setAdapter(adapter);
    }

    //when user clicks add to favourite this method executes
    public void clickAddToFavourites(View view){
        ArrayList<String> selectedFavourites = adapter.getSelectedFavourites(); //assigning array to the returned array

        Boolean checkUpdateData = false;
        for(String favourite: selectedFavourites){
            dbHelper.setFavourite(favourite);
            checkUpdateData = dbHelper.setFavourite(favourite);
        }

        //check whether if the data has been updated
        if (checkUpdateData){
            Toast.makeText(this," Entry Updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this," Entry Not Updated", Toast.LENGTH_SHORT).show();
        }

    }

}
