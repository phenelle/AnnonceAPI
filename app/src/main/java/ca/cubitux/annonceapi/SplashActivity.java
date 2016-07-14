package ca.cubitux.annonceapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cubitux.model.User;

import ca.cubitux.annonceapi.tasks.IsAuthLoadAsyncTask;

/**
 * Created by pierre on 2016-07-10.
 */
public class SplashActivity extends AppCompatActivity implements AsyncTaskListener {

    /**
     * Variable that will hold user's session (if any)
     */
    private String PREFERENCES = "UserPreferences";

    private IsAuthLoadAsyncTask mAsyncTask;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Restore preferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, 0);
        String session = sharedPreferences.getString("user_session", null);
        if (session != null) {
            mUser = new User();
            mUser.setSession(session);
            mAsyncTask = new IsAuthLoadAsyncTask(this, mUser);
            mAsyncTask.showProgress(true);
            mAsyncTask.execute((Void) null);
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
    }
}
