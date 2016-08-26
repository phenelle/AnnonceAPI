package ca.cubitux.annonceapi;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cubitux.model.annonce.Annonce;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class DetailActivity extends Activity {

    /**
     * Annonce to display
     */
    private Annonce mAnnonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        // Annonce to display
        Bundle extras = getIntent().getExtras();
        mAnnonce = (Annonce) extras.getSerializable("Annonce");
        if (mAnnonce != null) {
            // Load main image
            ImageView imageView = (ImageView) this.findViewById(R.id.image);
            imageView.setImageResource(android.R.color.transparent);
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(mAnnonce.getImage(), imageView);

            TextView titleView = (TextView) this.findViewById(R.id.title);
            titleView.setText(mAnnonce.getTitle());

            TextView descView = (TextView) this.findViewById(R.id.description);
            descView.setText(mAnnonce.getDescription());
        }
    }
}
