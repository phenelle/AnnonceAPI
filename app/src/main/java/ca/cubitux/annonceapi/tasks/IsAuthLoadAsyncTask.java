package ca.cubitux.annonceapi.tasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.cubitux.controller.UserCtrl;
import com.cubitux.model.User;

/**
 * Created by pierre on 2016-07-14.
 */
public class IsAuthLoadAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private Activity mActivity;

    private User mUser;

    public IsAuthLoadAsyncTask(Activity activity, User user) {
        mActivity = activity;
        mUser = user;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            UserCtrl.isAuthenticate(mUser);
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
            asyncTaskListener.onPostExecute(success);
        }
    }

}
