package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    private EditText titleMovie, yearMovie, directorMovie, actorsMovie, reviewMovie;
    private Spinner ratingSpinner;
    private final Integer[] ratingsRange = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register Movie");

        //referencing views
        titleMovie = findViewById(R.id.titleMovie);
        yearMovie = findViewById(R.id.yearMovie);
        directorMovie = findViewById(R.id.directorMovie);
        actorsMovie = findViewById(R.id.actorsMovie);
        reviewMovie = findViewById(R.id.reviewMovie);
        ratingSpinner = findViewById(R.id.ratingSpinner);

        dbHelper = new DatabaseHelper(this);

        //assigning values to the spinner
        ArrayAdapter<Integer> ratingsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                ratingsRange
        );

        ratingSpinner.setAdapter(ratingsAdapter);

    }

    public void clickSave(View view){
        String title = titleMovie.getText().toString();
        String year = yearMovie.getText().toString();
        String director = directorMovie.getText().toString();
        String actors = actorsMovie.getText().toString();
        String review = reviewMovie.getText().toString();
        int rating = (Integer)ratingSpinner.getSelectedItem();
        int favourite = 0;

        if (!title.isEmpty()){
            if (year.isEmpty()){
                Boolean checkInsertData = dbHelper.insertData(title,year,director,actors,review,rating,favourite);
                if (checkInsertData){
                    Toast.makeText(this,"Data Inserted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"Movie already exists", Toast.LENGTH_SHORT).show();
                }
            }else {
                if (Integer.parseInt(year) > 1895){

                    Boolean checkInsertData = dbHelper.insertData(title,year,director,actors,review,rating,favourite);
                    if (checkInsertData){
                        Toast.makeText(this,"Data Inserted", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,"Movie already exists", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"Year should be recent than 1895", Toast.LENGTH_SHORT).show();
                    yearMovie.setText("");
                }
            }
        }else {
            //giving a warning toast message because titles cant be empty
            Toast.makeText(this,"Movie Title shouldn't be empty", Toast.LENGTH_SHORT).show();
        }









    }


}