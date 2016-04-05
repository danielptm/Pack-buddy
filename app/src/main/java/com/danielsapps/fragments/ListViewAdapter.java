package com.danielsapps.fragments;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielsapps.model.ProfileBean;
import com.danielsapps.packbuddy.R;
import com.danielsapps.packbuddycontroller.DataConversion;

import java.util.ArrayList;

/**
 * Created by daniel on 4/5/16.
 */
public class ListViewAdapter extends ArrayAdapter<ProfileBean> {
    public ListViewAdapter(Context context, ArrayList<ProfileBean> alpfbs){
        super(context, R.layout.checked_in_row, alpfbs );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater li = LayoutInflater.from(getContext());
        View cView = li.inflate(R.layout.checked_in_row, parent, false);

        ProfileBean pfb = getItem(position);
        TextView nameText = (TextView) cView.findViewById(R.id.listViewName);
        TextView cityText = (TextView) cView.findViewById(R.id.listViewCity);
        ImageView pic = (ImageView) cView.findViewById(R.id.listViewImage);
        pic.setRotation(270);

        nameText.setText(pfb.getName());
        cityText.setText(pfb.getHomeCity());
        pic.setImageBitmap(DataConversion.getImageAsBitmap(pfb.getImg()));

        return cView;
    }
}
