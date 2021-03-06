package ca.cubitux.annonceapi.tasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.cubitux.controller.UserCtrl;
import com.cubitux.model.User;

/**
 * Created by pierre on 2016-07-21.
 */
public class CheckEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private Activity mActivity;

    private User mUser;

    private String mEmailToCheck;

    public CheckEmailAsyncTask(Activity activity, String emailToCheck) {
        mActivity = activity;
        mEmailToCheck = emailToCheck;
    }

    public User getUser() {
        return mUser;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            UserCtrl.checkEmailAvailable(mEmailToCheck);
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
