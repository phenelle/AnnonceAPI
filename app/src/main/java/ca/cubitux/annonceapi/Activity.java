package ca.cubitux.annonceapi;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pierre on 2016-07-17.
 */
public class Activity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    /**
     * Show or hide loading throbber with the default message
     *
     * @param show
     */
    protected void showProgress(boolean show) {
        this.showProgress(show, getResources().getString(R.string.please_wait));
    }

    /**
     * Show or hide loading throbber with a custom message
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
