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

        Thread splashTimer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Get last user's session
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, 0);
                    String session = sharedPreferences.getString("user_session", null);
                    if (session != null) {
                        // Verify if it is still valid
                        mUser = new User();
                        mUser.setSession(session);
                        mAsyncTask = new IsAuthLoadAsyncTask(SplashActivity.this, mUser);
                        mAsyncTask.execute((Void) null);
                    } else {
                        // Otherwise, launch Login activity
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        SplashActivity.this.finish();
                    }
                }
            }
        };
        splashTimer.start();

    }

    @Override
    public void onPostExecute(Boolean success) {
        if (success) {
            Intent homeActivity = new Intent(this, HomeActivity.class);
            homeActivity.putExtra("User", mUser);
            startActivity(homeActivity);
            SplashActivity.this.finish();
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            SplashActivity.this.finish();
        }
        // close current activity
    }
}
