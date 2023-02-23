
package com.example.coursework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;


import java.util.ArrayList;

public class MovieListAdapter extends ArrayAdapter<String> {
    private ArrayList<String> movieNames;
    private ArrayList<String> favouriteMovies;
    private ArrayList<String> selectedFavourites = new ArrayList<>();

    public MovieListAdapter(Context context, int resource, ArrayList<String> movies,ArrayList<String> favourites) {
        super(context, resource, movies);

        this.movieNames = new ArrayList<>();
        this.movieNames.addAll(movies);

        this.favouriteMovies = new ArrayList<>();
        this.favouriteMovies.addAll(favourites);

    }

    static class ViewHolder {
        TextView movieTitle;
        CheckBox checkBox;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_row,null);

            viewHolder.movieTitle = convertView.findViewById(R.id.movieTitle);
            viewHolder.checkBox = convertView.findViewById(R.id.checkBox);

            convertView.setTag(viewHolder);

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    CheckBox checkedCheckBox = (CheckBox) buttonView;
                    if (isChecked){  //if a check box is checked corresponding title will be added to the list
                        selectedFavourites.add(checkedCheckBox.getTag().toString());

                    }else{
                        selectedFavourites.remove(checkedCheckBox.getTag().toString());
                    }
                }
            });

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String movieName = movieNames.get(position);

        viewHolder.movieTitle.setText(movieName);
        viewHolder.checkBox.setTag(movieName);

        //sets the checkBoxes checked if their corresponding titles are favourite
        for (String favourite:favouriteMovies){
            if (viewHolder.checkBox.getTag().toString().equals(favourite)){
                viewHolder.checkBox.setChecked(true);
                viewHolder.checkBox.setEnabled(false);
            }
        }

        return convertView;
    }

    public ArrayList<String> getSelectedFavourites(){
        return selectedFavourites;
    }





}
