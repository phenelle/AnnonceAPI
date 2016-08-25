package ca.cubitux.annonceapi;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.View;

/**
 * Abstract class that handle Side Drawer
 */
public abstract class DrawerActivity extends Activity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * NavigationView is the sidebar
     */
    protected NavigationView mNavigationView;

    /**
     * Header sidebar when logged / unlogged
     */
    protected View headerViewLogged, headerViewUnlogged;

    /**
     * Menu of the sidebar
     */
    protected Menu mMenu;

    /**
     * OnClick for the SignIn Button
     *
     * @param view
     */
    public void signInButtonOnClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }

}
