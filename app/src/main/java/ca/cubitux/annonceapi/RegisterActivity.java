package ca.cubitux.annonceapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import ca.cubitux.annonceapi.tasks.AsyncTaskListener;
import ca.cubitux.annonceapi.tasks.CheckEmailAsyncTask;

public class RegisterActivity extends Activity implements AsyncTaskListener {

    private EditText mEmailView;

    private CheckEmailAsyncTask mAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = (EditText) findViewById(R.id.email);
    }


    /**
     * Verify the email when button clicked
     *
     * @param view
     */
    public void checkEmailOnClick(View view) {
        String email = mEmailView.getText().toString();

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            return;
        } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            return;
        }

        showProgress(true);
        mAsyncTask = new CheckEmailAsyncTask(this, email);
        mAsyncTask.execute((Void) null);
    }

    @Override
    public void onPostExecute(Boolean success, AsyncTask asyncTask) {
        showProgress(false);
        if (success) {
            //
        } else {
            mEmailView.setError("Email already in use");
        }
    }

}
