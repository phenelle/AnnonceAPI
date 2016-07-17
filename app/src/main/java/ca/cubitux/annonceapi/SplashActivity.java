package ca.cubitux.annonceapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.cubitux.model.User;

import ca.cubitux.annonceapi.tasks.AsyncTaskListener;
import ca.cubitux.annonceapi.tasks.IsAuthAsyncTask;

/**
 * Created by pierre on 2016-07-10.
 */
public class SplashActivity extends Activity implements AsyncTaskListener {

    /**
     * Variable that will hold user's session (if any)
     */
    private String PREFERENCES = "UserPreferences";

    /**
     * Verify if logged task
     */
    private IsAuthAsyncTask mAsyncTask;

    /**
     * Current user
     */
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Create an empty user
        mUser = new User();

        // Allow to see the splash screen for 1 second
        Thread splashTimer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    Log.d(this.getName(), "Exception with Thread.sleep()", e);
                } finally {

                    // Get last user's session
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, 0);
                    String session = sharedPreferences.getString("user_session", null);

                    if (session != null) {
                        // Verify if session is still active
                        mUser.setSession(session);
                        mAsyncTask = new IsAuthAsyncTask(SplashActivity.this, mUser);
                        mAsyncTask.execute((Void) null);
                    } else {
                        Intent homeActivity = new Intent(SplashActivity.this, HomeActivity.class);

                        // Pass mUser and then start Activity
                        homeActivity.putExtra("User", mUser);
                        startActivity(homeActivity);

                        // close current activity
                        SplashActivity.this.finish();
                    }
                }
            }
        };
        splashTimer.start();

    }

    @Override
    public void onPostExecute(Boolean success) {
        Intent homeActivity = new Intent(this, HomeActivity.class);

        // Pass mUser and then start Activity
        homeActivity.putExtra("User", mUser);
        startActivity(homeActivity);

        // close current activity
        SplashActivity.this.finish();
    }
}
