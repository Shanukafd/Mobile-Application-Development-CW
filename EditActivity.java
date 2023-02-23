package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    private  ArrayList<String> movieTitles = new ArrayList<>();

    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> years = new ArrayList<>();
    ArrayList<String> directors = new ArrayList<>();
    ArrayList<String> actors = new ArrayList<>();
    ArrayList<String> reviews = new ArrayList<>();
    ArrayList<String> ratings = new ArrayList<>();
    ArrayList<String> favourites = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Edit Movies");

        ListView editList = findViewById(R.id.editList);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor result = dbHelper.getMovieNameUnordered();

        //check whether the cursor has data and adding them to arraylists
        if (result.getCount() == 0){
            Toast.makeText(this,"No Entry exists", Toast.LENGTH_SHORT).show();
        }else {
            while (result.moveToNext()){


                ids.add(result.getString(0));
                movieTitles.add(result.getString(1));
                years.add(result.getString(2));
                directors.add(result.getString(3));
                actors.add(result.getString(4));
                reviews.add(result.getString(5));
                ratings.add(result.getString(6));
                favourites.add(result.getString(7));
            }
        }

        //adding values to the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                movieTitles);

        editList.setAdapter(adapter);

        editList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EditActivity.this, UpdateActivity.class);

                //passing intent data to the next screen
                intent.putExtra("id", String.valueOf(ids.get(position)));
                intent.putExtra("title", String.valueOf(movieTitles.get(position)));
                intent.putExtra("years", String.valueOf(years.get(position)));
                intent.putExtra("directors", String.valueOf(directors.get(position)));
                intent.putExtra("actors", String.valueOf(actors.get(position)));
                intent.putExtra("reviews", String.valueOf(reviews.get(position)));
                intent.putExtra("ratings", String.valueOf(ratings.get(position)));
                intent.putExtra("favourites", String.valueOf(favourites.get(position)));

                startActivity(intent);
            }
        });



    }
}