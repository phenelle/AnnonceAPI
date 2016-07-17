package ca.cubitux.annonceapi.permissions;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * Created by pierre on 2016-07-17.
 */
public class RequestPermissions {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * Verify if application can read contact, or request permission
     *
     * @param activity
     * @return
     */
    public static boolean canReadContacts(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (activity.checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        activity.requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        return false;
    }


}
