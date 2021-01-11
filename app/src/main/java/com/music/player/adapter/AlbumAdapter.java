package com.music.player.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.music.player.model.Album;

import java.util.ArrayList;

public class AlbumAdapter extends ArrayAdapter<Album> {

    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        super(context, 0, albums);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater albumInflater = LayoutInflater.from(getContext());
        View customView = albumInflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);

        String album_name = getItem(position).getTitle();
        TextView album_text = (TextView) customView.findViewById(android.R.id.text1);
        album_text.setText(album_name);

        return customView;
    }
}