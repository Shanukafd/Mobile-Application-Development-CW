package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private EditText searchEdtText;
    private ListView searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search Movies");

        searchEdtText = findViewById(R.id.searchEditText);
        searchList = findViewById(R.id.searchedList);

    }

    public void Search(View view){
        String searchText = searchEdtText.getText().toString().toLowerCase();  //takes the users input

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor result1 = dbHelper.getSearchedTitle(searchText);
        Cursor result2 = dbHelper.getSearchedDirector(searchText);
        Cursor result3 = dbHelper.getSearchedActor(searchText);

        ArrayList<String> searchesItems = new ArrayList<>();

        if (result1.getCount() == 0 && result2.getCount() == 0 && result3.getCount() == 0){
            Toast.makeText(this,"No Entry exists", Toast.LENGTH_SHORT).show();
        }else {
            while (result1.moveToNext()){
                searchesItems.add(result1.getString(0) + "     (Movie Title)");

            }
            while (result2.moveToNext()){
                searchesItems.add(result2.getString(0) + "     (Director)");

            }
            while (result3.moveToNext()){
                searchesItems.add(result3.getString(0) + "     (Actor)");

            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                searchesItems);

        searchList.setAdapter(adapter);

    }
}