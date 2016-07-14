package ca.cubitux.annonceapi;

import android.app.Activity;

/**
 * Created by pierre on 2016-07-14.
 */
public interface AsyncTaskListener {

    Activity mActivity = null;

    void onPostExecute(final Boolean success);
}
