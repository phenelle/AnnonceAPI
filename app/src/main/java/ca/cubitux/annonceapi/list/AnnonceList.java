package ca.cubitux.annonceapi.list;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cubitux.model.annonce.Annonce;
import com.cubitux.utils.HTTPUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ca.cubitux.annonceapi.R;

/**
 * Created by pierre on 2016-07-16.
 */
public class AnnonceList extends ArrayAdapter<String> {


    private Activity mActivity;
    private List<Annonce> mAnnonces;

    public AnnonceList(Activity activity, List<Annonce> annonces) {
        super(activity, R.layout.content_home_listview_item);
        this.mActivity = activity;
        this.mAnnonces = annonces;
    }

    private Drawable loadImage(String imageUrl) {
        // @TODO replace abstract_background with default annonce image
        Drawable drawable = mActivity.getResources().getDrawable(R.drawable.abstract_background);
        try {
            InputStream inputStream = HTTPUtil.httpStream(imageUrl);
            drawable = Drawable.createFromStream(inputStream, null);
        } catch (IOException e) {
        }
        return drawable;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.content_home_listview_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(mAnnonces.get(position).getTitle());
        Drawable imageDrawable = loadImage(mAnnonces.get(position).getImage());
        imageView.setImageDrawable(imageDrawable);
        return rowView;
    }
}

