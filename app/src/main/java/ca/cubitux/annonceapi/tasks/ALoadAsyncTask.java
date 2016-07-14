package ca.cubitux.annonceapi.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * Created by pierre on 2016-07-14.
 */
abstract class ALoadAsyncTask extends AsyncTask<Void, Void, Boolean> {

    /**
     * Loading spinner
     */
    private ProgressDialog mProgress;

    /**
     * Constructor
     *
     * @param activity, current activity
     */
    public ALoadAsyncTask(Activity activity) {
        if (mProgress == null) {
            mProgress = new ProgressDialog(activity);
        }
    }

    /**
     * Show or hide loading spinner
     *
     * @param show
     */
    public void showProgress(final boolean show) {

        mProgress.setTitle("Loading");
        mProgress.setMessage("Wait while loading annonce list...");

        if (show) {
            mProgress.show();
        } else {
            mProgress.dismiss();
        }
    }
}
