package ca.cubitux.annonceapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cubitux.model.User;

import ca.cubitux.annonceapi.tasks.AsyncTaskListener;
import ca.cubitux.annonceapi.tasks.IsAuthLoadAsyncTask;

/**
 * Created by pierre on 2016-07-10.
 */
public class SplashActivity extends AppCompatActivity implements AsyncTaskListener {

    /**
     * Variable that will hold user's session (if any)
     */
    private String PREFERENCES = "UserPreferences";

    /**
     * Verify if logged task
     */
    private IsAuthLoadAsyncTask mAsyncTask;

    /**
     * Current user
     */
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Make it wait a little, so we can see the splash screen
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        // Get last user's session
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, 0);
        String session = sharedPreferences.getString("user_session", null);
        if (session != null) {
            // Verify if it is still valid
            mUser = new User();
            mUser.setSession(session);
            mAsyncTask = new IsAuthLoadAsyncTask(this, mUser);
            mAsyncTask.execute((Void) null);
        } else {
            // Otherwise, launch Login activity
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void onPostExecute(Boolean success) {
        if (success) {
            Intent homeActivity = new Intent(this, HomeActivity.class);
            homeActivity.putExtra("User", mUser);
            startActivity(homeActivity);
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        // close current activity
        finish();
    }
}
