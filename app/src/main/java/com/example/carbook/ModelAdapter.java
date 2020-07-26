package com.example.carbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ModelAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<CarModelClass> liste;

    public ModelAdapter(Context context,int layout, ArrayList<CarModelClass> liste) {
        this.context = context;
        this.layout = layout;
        this.liste = liste;
    }
    @Override
    public int getCount() {
        return liste.size();
    }
    @Override
    public Object getItem(int position) {
        return liste.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layout,null);
        ImageView img = convertView.findViewById(R.id.customimg);
        TextView txtAd = convertView.findViewById(R.id.customCarName);
        TextView txtAciklama = convertView.findViewById(R.id.customCarDesc);
        Picasso.get().load(liste.get(position).getPoster()).into(img);
        txtAd.setText(liste.get(position).getCarModelName());
        txtAciklama.setText(liste.get(position).getCarModelDesc());
        return convertView;
    }
}
