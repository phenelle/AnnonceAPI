package ca.cubitux.annonceapi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Abstract class with common properties to all Activities
 */
public abstract class Activity extends AppCompatActivity {

    /**
     * Loading progress dialog
     */
    private ProgressDialog progressDialog;

    /**
     * Show or hide "loading animation" with the default message
     *
     * @param show
     */
    protected void showProgress(boolean show) {
        this.showProgress(show, getResources().getString(R.string.please_wait));
    }

    /**
     * Show or hide "loading animation" with a custom message
     *
     * @param show
     * @param message
     */
    protected void showProgress(boolean show, String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this, R.style.Theme_AlertDialogPro_Light);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle(R.string.loading);
            progressDialog.setMessage(message);
        }
        if (show) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


}
