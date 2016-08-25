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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cubitux.model.User;
import com.cubitux.model.annonce.Annonce;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import ca.cubitux.annonceapi.adapter.AnnonceAdapter;
import ca.cubitux.annonceapi.tasks.AnnonceAsyncTask;
import ca.cubitux.annonceapi.tasks.AsyncTaskListener;
import ca.cubitux.annonceapi.tasks.LogoutAsyncTask;

public class HomeActivity extends DrawerActivity implements AsyncTaskListener {

    /**
     * Current user
     */
    private User mUser;

    /**
     * Latest annonce
     */
    private List<Annonce> mAnnonces;

    /**
     * Used by the navigation drawer
     */
    private TextView mUserFullname, mUserEmail;

    /**
     * Logout task
     */
    private LogoutAsyncTask mLogoutTask;

    /**
     * Load latest annonce task
     */
    private AnnonceAsyncTask mAnnonceTask;

    /**
     * List of latest annonce
     */
    private ListView mListView;

    /**
     * Annonce Adapter
     */
    private AnnonceAdapter mAnnonceAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

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

        // Initialize empty listview
        mListView = (ListView) findViewById(R.id.listView);
        mAnnonces = new ArrayList<Annonce>();
        mAnnonceAdapter = new AnnonceAdapter(HomeActivity.this, mAnnonces);
        mListView.setAdapter(mAnnonceAdapter);

        // Fetch remote annonces
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        if (mUser.isLogged()) {
            menu.getItem(0).setVisible(true);
        } else {
            menu.getItem(0).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            showProgress(true);
            mLogoutTask = new LogoutAsyncTask(this, mUser.getSession());
            mLogoutTask.execute((Void) null);
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

        // Logout
        if (success && asyncTask instanceof LogoutAsyncTask) {
            mUser = ((LogoutAsyncTask) asyncTask).getUser();
            headerViewUnlogged = getLayoutInflater().inflate(R.layout.side_nav_unlogged_drawer, mNavigationView);
            mMenu.findItem(R.id.nav_group_profile).setVisible(false);
            invalidateOptionsMenu();
            mLogoutTask = null;
        }

        // Annonce List
        if (success && asyncTask instanceof AnnonceAsyncTask) {

            mAnnonces = ((AnnonceAsyncTask) asyncTask).getAnnonces();
            mAnnonceAdapter = new AnnonceAdapter(HomeActivity.this, mAnnonces);
            mListView.setAdapter(mAnnonceAdapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Start new activity
                    Intent detailActivity = new Intent(HomeActivity.this, DetailActivity.class);
                    detailActivity.putExtra("Annonce", mAnnonces.get(position));
                    startActivity(detailActivity);
                    HomeActivity.this.finish();
                }
            });
            mAnnonceTask = null;
        }


    }
}
