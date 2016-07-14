package ca.cubitux.annonceapi.tasks;

import android.app.Activity;

import com.cubitux.controller.UserCtrl;
import com.cubitux.model.User;

import ca.cubitux.annonceapi.AsyncTaskListener;

/**
 * Created by pierre on 2016-07-14.
 */
public class IsAuthLoadAsyncTask extends ALoadAsyncTask {

    private Activity mActivity;

    private User mUser;

    public IsAuthLoadAsyncTask(Activity activity, User user) {
        super(activity);
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
        showProgress(false);
        if (mActivity instanceof AsyncTaskListener) {
            AsyncTaskListener asyncTaskListener = (AsyncTaskListener) mActivity;
            asyncTaskListener.onPostExecute(success);
        }
    }

    @Override
    protected void onCancelled() {
        showProgress(false);
    }
}
