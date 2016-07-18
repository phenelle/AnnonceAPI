package ca.cubitux.annonceapi.tasks;

/**
 * Created by pierre on 2016-07-14.
 */

import android.app.Activity;
import android.os.AsyncTask;

import com.cubitux.controller.AnnonceCtrl;
import com.cubitux.model.Category;
import com.cubitux.model.annonce.Annonce;

import java.util.List;

/**
 * Represents an asynchronous login/registration task used to authenticate
 * the user.
 */
public class AnnonceAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private List<Annonce> mAnnonces;
    private Activity mActivity;

    public AnnonceAsyncTask(Activity activity) {
        mActivity = activity;
    }

    public List<Annonce> getAnnonces() {
        return mAnnonces;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            mAnnonces = AnnonceCtrl.list(Category.ALL);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        super.onPostExecute(success);
        if (mActivity instanceof AsyncTaskListener) {
            AsyncTaskListener asyncTaskListener = (AsyncTaskListener) mActivity;
            asyncTaskListener.onPostExecute(success, this);
        }
    }
}