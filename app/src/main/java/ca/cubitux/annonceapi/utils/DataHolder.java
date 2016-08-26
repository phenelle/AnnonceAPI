package ca.cubitux.annonceapi.utils;

import com.cubitux.model.User;
import com.cubitux.model.annonce.Annonce;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that allow to share data between activities
 *
 * @author Pierre
 * @version 1.0
 */
public class DataHolder {

    /**
     * Singleton instance
     */
    private static DataHolder holder;

    private User mUser = new User();

    private List<Annonce> mAnnonces = new ArrayList<Annonce>();


    /**
     * Singleton method to XXX
     *
     * @return
     */
    public static DataHolder getInstance() {
        if (holder == null) {
            holder = new DataHolder();
        }
        return holder;
    }


    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        this.mUser = user;
    }

    public List<Annonce> getAnnonces() {
        return this.mAnnonces;
    }

    public void setAnnonces(List<Annonce> annonces) {
        this.mAnnonces = annonces;
    }
}
