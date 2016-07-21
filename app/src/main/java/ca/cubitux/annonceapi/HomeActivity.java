package ca.cubitux.annonceapi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cubitux.model.User;
import com.cubitux.model.annonce.Annonce;

import java.util.List;

import ca.cubitux.annonceapi.list.AnnonceList;
import ca.cubitux.annonceapi.tasks.AnnonceAsyncTask;
import ca.cubitux.annonceapi.tasks.AsyncTaskListener;
import ca.cubitux.annonceapi.tasks.LogoutAsyncTask;

public class HomeActivity extends Activity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncTaskListener {

    /**
     * Used by the navigation drawer
     */
    private TextView mUserFullname, mUserEmail;

    /**
     * Current user
     */
    private User mUser;

    /**
     * Latest annonce
     */
    private List<Annonce> mAnnonces;

    /**
     * Logout task
     */
    private LogoutAsyncTask mLogoutTask;

    /**
     * Load latest annonce task
     */
    private AnnonceAsyncTask mAnnonceTask;

    /**
     * NavigationView is the sidebar
     */
    private NavigationView mNavigationView;

    /**
     * Header sidebar when logged / unlogged
     */
    private View headerViewLogged, headerViewUnlogged;

    /**
     * Menu of the sidebar
     */
    private Menu mMenu;

    /**
     * List of latest annonce
     */
    private ListView mListView;

    /**
     * OnClick for the SignIn Button
     *
     * @param view
     */
    public void signInButtonOnClick(View view) {
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        HomeActivity.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home - AnnonceAPI");
        setSupportActionBar(toolbar);

        // Floating button (right bottom corner)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Publish new annonce", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Get NavigationViews
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        // Bind click to unlogged HeaderView
        headerViewUnlogged = mNavigationView.getHeaderView(0);

        mMenu = mNavigationView.getMenu();
        mMenu.findItem(R.id.nav_group_profile).setVisible(false);

        // Get currently logged user
        Bundle extras = getIntent().getExtras();
        mUser = (User) extras.getSerializable("User");
        if (mUser != null && mUser.isLogged()) {

            // Inflate logged HeaderView
            headerViewLogged = getLayoutInflater().inflate(R.layout.side_nav_logged_drawer, mNavigationView);
            headerViewUnlogged.setVisibility(View.INVISIBLE);

            // Toggle visibility
            headerViewLogged.setVisibility(View.VISIBLE);
            mMenu.findItem(R.id.nav_group_profile).setVisible(true);

            // Configure user's fullname and email
            mUserFullname = (TextView) headerViewLogged.findViewById(R.id.userFullname);
            mUserFullname.setText(mUser.getFirstName() + " " + mUser.getLastName());
            mUserEmail = (TextView) headerViewLogged.findViewById(R.id.userEmail);
            mUserEmail.setText(mUser.getEmail());
        }

        mAnnonceTask = new AnnonceAsyncTask(this);
        mAnnonceTask.execute((Void) null);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_publish_annonce) {
            // Handle publication
        } else if (id == R.id.nav_edit_profile) {
            // Handle edit profile
        } else if (id == R.id.nav_logout) {
            showProgress(true);
            mLogoutTask = new LogoutAsyncTask(this, mUser.getSession());
            mLogoutTask.execute((Void) null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onPostExecute(Boolean success, AsyncTask asyncTask) {
        showProgress(false);
        if (success && asyncTask instanceof LogoutAsyncTask) {
            mUser = ((LogoutAsyncTask) asyncTask).getUser();
            headerViewUnlogged = getLayoutInflater().inflate(R.layout.side_nav_unlogged_drawer, mNavigationView);
            mMenu.findItem(R.id.nav_group_profile).setVisible(false);
            mLogoutTask = null;
        }

        if (success && asyncTask instanceof AnnonceAsyncTask) {

            // Load Annonce List
            mAnnonces = ((AnnonceAsyncTask) asyncTask).getAnnonces();
            AnnonceList adapter = new AnnonceList(HomeActivity.this, mAnnonces);
            mListView = (ListView) findViewById(R.id.listView);
            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });

            mAnnonceTask = null;
        }


    }
}
