package com.example.gymbuddies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserCardArrayAdapter extends ArrayAdapter<UserCard> {

    public UserCardArrayAdapter(@NonNull Context context, int resource, @NonNull List<UserCard> objects) {
        super(context, resource, objects);
    }
    public View getView(int position, View view, ViewGroup parent){
        UserCard item = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        TextView name = (TextView) view.findViewById(R.id.name);
        ImageView image = (ImageView) view.findViewById(R.id.image);

        name.setText(item.getName());
        //set launch image now, will update later to use actual image urls
        //image.setImageResource(R.mipmap.ic_launcher);
        Glide.with(getContext()).load(item.getProfileImg()).into(image);


        return view;
    }
}
