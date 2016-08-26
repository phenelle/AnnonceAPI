package ca.cubitux.annonceapi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cubitux.model.annonce.Annonce;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import ca.cubitux.annonceapi.R;

/**
 * Created by pierre on 2016-07-16.
 */
public class AnnonceAdapter extends ArrayAdapter<Annonce> {

    private Context mContext;
    private List<Annonce> mAnnonces;

    public AnnonceAdapter(Context context, List<Annonce> annonces) {
        super(context, R.layout.listview_item, annonces);
        this.mContext = context;
        this.mAnnonces = annonces;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_item, parent, false);

        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView desc = (TextView) rowView.findViewById(R.id.description);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
        imageView.setBackgroundColor(Color.WHITE);

        Annonce mAnnonce = mAnnonces.get(position);

        title.setText(mAnnonce.getTitle());
        desc.setText(mAnnonce.getDescription());

        // Get singleton instance
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(mAnnonce.getImage(), imageView);
        return rowView;
    }

}

