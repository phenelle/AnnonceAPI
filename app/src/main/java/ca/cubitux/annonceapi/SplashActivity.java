package ca.cubitux.annonceapi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cubitux.controller.AnnonceCtrl;
import com.cubitux.model.Category;

/**
 * Created by pierre on 2016-07-10.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private LoadAnnonceTask mLoadTask;

    /**
     * Loading spinner
     */
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        showProgress(true);
        mLoadTask = new LoadAnnonceTask();
        mLoadTask.execute((Void) null);
    }

    /**
     * Show or hide loading spinner
     *
     * @param show
     */
    private void showProgress(final boolean show) {
        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading annonce list...");
        }

        if (show) {
            progress.show();
        } else {
            progress.dismiss();
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class LoadAnnonceTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                AnnonceCtrl.list(Category.ALL);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            super.onPostExecute(success);
            mLoadTask = null;
            showProgress(false);

            if (success) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setTitle("Error");
                builder.setMessage("Cannot connect to the internet. Application will be closed.");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
            }
        }

        @Override
        protected void onCancelled() {
            mLoadTask = null;
            showProgress(false);
        }
    }

}
