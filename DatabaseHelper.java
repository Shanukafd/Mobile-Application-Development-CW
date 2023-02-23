package com.example.coursework2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {



    public DatabaseHelper(Context context) {
        super(context, "Movie_Details.db", null, 1);

    }

    //creates database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE Movie_Details(_Id INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT UNIQUE, Year INTEGER , Director TEXT, Actors TEXT, Reviews TEXT, Rating INTEGER, Favourite INTEGER )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Movie_Details");
    }

    public Boolean insertData(String title, String year, String director, String actors, String reviews, int rating, int favourite){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("Title",title);
        contentValues.put("Year",year);
        contentValues.put("Director",director);
        contentValues.put("Actors",actors);
        contentValues.put("Reviews",reviews);
        contentValues.put("Rating",rating);
        contentValues.put("Favourite",favourite);

        long result = DB.insert("Movie_Details", null , contentValues);
        if (result == -1){  //if data hasn't been inserted returns false
            return false;
        }else{
            return true;
        }
    }

    public Boolean setFavourite(String title){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Favourite",1);

        Cursor cursor = DB.rawQuery("SELECT * FROM Movie_Details WHERE Title = ?", new String[]{title});  //check whether title  matches with the passed value

        if (cursor.getCount() > 0) {
            long result = DB.update("Movie_Details",contentValues,"Title=?",new String[]{title});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Boolean updateFavourite(String title){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Favourite",0);

        Cursor cursor = DB.rawQuery("SELECT * FROM Movie_Details WHERE Title = ?", new String[]{title});   //check whether title  matches with the passed value

        if (cursor.getCount() > 0) {
            long result = DB.update("Movie_Details",contentValues,"Title=?",new String[]{title});
            if (result == -1) {  //if data hasn't been updated returns false
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Boolean updateRegistry(String id, String title,String year, String director, String actor, String review,String rating){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Title", title);
        contentValues.put("Year", year);
        contentValues.put("Director", director);
        contentValues.put("Actors", actor);
        contentValues.put("Reviews", review);
        contentValues.put("Rating",rating);



        long result = DB.update("Movie_Details",contentValues,"_Id=?",new String[]{id});

        if (result == -1) {    //if data hasn't been updated returns false
                return false;
            } else {
                return true;
            }

    }



    //returns movie titles in alphabetical order
    public Cursor getMovieTitle(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT Title FROM Movie_Details ORDER BY LOWER(Title)", null);

        return cursor;
    }

    //returns movie titles
    public Cursor getMovieNameUnordered(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM Movie_Details ", null);

        return cursor;
    }

    //returns favourite movies
    public Cursor getFavouriteMovies(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT Title FROM Movie_Details WHERE Favourite = 1 ORDER BY Title", null);

        return cursor;
    }


    //following methods returns searched data
    public Cursor getSearchedTitle(String searchText){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT Title FROM Movie_Details WHERE LOWER(Title) LIKE '%"+searchText+"%'" , null);

        return cursor;
    }

    public Cursor getSearchedDirector(String searchText){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT Director FROM Movie_Details WHERE LOWER(Director) LIKE '%"+searchText+"%'" , null);

        return cursor;
    }

    public Cursor getSearchedActor(String searchText){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT Actors FROM Movie_Details WHERE LOWER(Actors) LIKE '%"+searchText+"%'" , null);

        return cursor;
    }
}
