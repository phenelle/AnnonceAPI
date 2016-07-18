package ca.cubitux.annonceapi.tasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.cubitux.controller.UserCtrl;
import com.cubitux.model.User;

/**
 * Created by pierre on 2016-07-17.
 */
public class LogoutAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private Activity mActivity;

    private User mUser;

    private String mSession;

    public LogoutAsyncTask(Activity activity, String session) {
        mActivity = activity;
        mSession = session;
    }

    public User getUser() {
        return mUser;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            mUser = UserCtrl.logout(mSession);
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
