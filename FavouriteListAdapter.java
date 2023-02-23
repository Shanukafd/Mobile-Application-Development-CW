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

public class FavouriteListAdapter extends ArrayAdapter<String> {

    private ArrayList<String> favouriteList;
    private ArrayList<String> deselectedFavourites = new ArrayList<>();

    public FavouriteListAdapter(Context context, int resource, ArrayList<String> favourite) {
        super(context, resource, favourite);
        this.favouriteList = new ArrayList<>();
        favouriteList.addAll(favourite);

    }

    static class ViewHolder {
        TextView movieTitle;
        CheckBox checkBox;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        ViewHolder viewHolder =new ViewHolder();

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
                    if (!isChecked){   //if a checkBox is not checked add corresponding title to the list
                        deselectedFavourites.add(checkedCheckBox.getTag().toString());

                    }else{
                        deselectedFavourites.remove(checkedCheckBox.getTag().toString());
                    }
                }
            });

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String movieName = favouriteList.get(position);
        viewHolder.movieTitle.setText(movieName);
        viewHolder.checkBox.setTag(movieName);
        viewHolder.checkBox.setChecked(true);

        return convertView;

    }

    //returns list of deselected item to the favourite activity
    public ArrayList<String> getDeselectedFavourites(){
        return deselectedFavourites;
    }
}
