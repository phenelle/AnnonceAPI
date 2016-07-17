package ca.cubitux.annonceapi.list;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ca.cubitux.annonceapi.R;

/**
 * Created by pierre on 2016-07-16.
 */
public class AnnonceList extends ArrayAdapter<String> {


    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;

    public AnnonceList(Activity context, String[] web, Integer[] imageId) {
        super(context, R.layout.content_home_listview_item, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.content_home_listview_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}

