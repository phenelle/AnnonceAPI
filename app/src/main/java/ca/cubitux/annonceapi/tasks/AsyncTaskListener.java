package ca.cubitux.annonceapi.tasks;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by pierre on 2016-07-14.
 */
public interface AsyncTaskListener {

    Activity mActivity = null;

    void onPostExecute(final Boolean success, final AsyncTask asyncTask);
}
