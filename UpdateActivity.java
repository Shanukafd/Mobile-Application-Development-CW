package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText titleUpdate, yearUpdate, directorUpdate, actorUpdate, reviewUpdate;
    private String id;
    private String title;
    private String year;
    private String director;
    private String actor;
    private String review;
    private String newRatingValue;
    private CheckBox favCheckbox;
    private RatingBar favRatingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setTitle("Update Details");

        //referencing views
        titleUpdate = findViewById(R.id.titleMovieUpdate);
        yearUpdate = findViewById(R.id.yearMovieUpdate);
        directorUpdate = findViewById(R.id.directorMovieUpdate);
        actorUpdate = findViewById(R.id.actorsMovieUpdate);
        reviewUpdate = findViewById(R.id.reviewMovieUpdate);
        favCheckbox = findViewById(R.id.favCheckbox);
        favRatingBar = findViewById(R.id.favRating);

        getAndSetIntentData();

        favRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int b = (int)favRatingBar.getRating();
                newRatingValue = String.valueOf(b);
            }
        });

    }

    public void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("years") && getIntent().hasExtra("directors")
        && getIntent().hasExtra("actors") && getIntent().hasExtra("reviews") && getIntent().hasExtra("ratings") && getIntent().hasExtra("favourites")){

            //gets the intent data
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            year = getIntent().getStringExtra("years");
            director = getIntent().getStringExtra("directors");
            actor = getIntent().getStringExtra("actors");
            review = getIntent().getStringExtra("reviews");
            String ratingValue = getIntent().getStringExtra("ratings");
            String favourite = getIntent().getStringExtra("favourites");

            //sets the intent data
            titleUpdate.setText(title);
            yearUpdate.setText(year);
            directorUpdate.setText(director);
            actorUpdate.setText(actor);
            reviewUpdate.setText(review);
            favRatingBar.setRating(Integer.parseInt(ratingValue));

            if (Integer.parseInt(favourite) == 1){
                favCheckbox.setChecked(true);
                favCheckbox.setText("Favourite");
                favCheckbox.setEnabled(false);
            }else{
                favCheckbox.setEnabled(false);
                favCheckbox.setText("Not Favourite");
            }



        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateRegistry(View view){
        DatabaseHelper dbHelper = new DatabaseHelper(UpdateActivity.this);

        //gets the data from the views
        title = titleUpdate.getText().toString().trim();
        year = yearUpdate.getText().toString().trim();
        director = directorUpdate.getText().toString().trim();
        actor = actorUpdate.getText().toString().trim();
        review = reviewUpdate.getText().toString().trim();

        //if the new rating value is null sets to the previous value
        if (newRatingValue == null){
            newRatingValue = String.valueOf(favRatingBar.getRating());
        }


        Boolean checkUpdateData = dbHelper.updateRegistry(id,title,year,director,actor,review,newRatingValue);

        if (checkUpdateData){
            Toast.makeText(UpdateActivity.this," Entry Updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(UpdateActivity.this," Entry Not Updated", Toast.LENGTH_SHORT).show();
        }
    }
}