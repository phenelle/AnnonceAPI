package ca.cubitux.annonceapi;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.view.accessibility.AccessibilityNodeInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class LoginActivityTest {

    /**
     * 5 seconds should be enough
     */
    private static final int LAUNCH_TIMEOUT = 5000;

    /**
     * Wait 1 secondes to find components of the graphical interface
     */
    private static final int COMPONENT_TIMEOUT = 1000;

    /**
     * Package where to find all classes
     */
    private final String TARGET_PACKAGE = "ca.cubitux.annonceapi";
    /**
     * Path to the Activity
     */
    private final String TARGET_ACTIVITY = "ca.cubitux.annonceapi.LoginActivity";
    /**
     * Our virtual device
     */
    private UiDevice mDevice;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    /**
     *  Get the mError value of any View (For API > 21)
     */
    private String getErrorOfUIObject2(UiObject2 uiObject2) {
        String error = null;
        try {
            Field f = uiObject2.getClass().getDeclaredField("mCachedNode");
            f.setAccessible(true);
            Object mCachedNode = f.get(uiObject2);
            AccessibilityNodeInfo nodeInfo = (AccessibilityNodeInfo) mCachedNode;
            error = (String) nodeInfo.getError();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return error;
    }

    @Before
    public void before() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        assertThat(mDevice, notNullValue());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        Intent intent = new Intent();
        intent.setClassName(TARGET_PACKAGE, TARGET_ACTIVITY);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(TARGET_ACTIVITY).depth(0)), LAUNCH_TIMEOUT);
    }


    @Test
    public void testInvalidEmail() throws InterruptedException {

        // The field resource identifier
        final String emailTextViewResId = "email";

        // Find email field (and it's current state)
        UiObject2 emailTextView = mDevice
                .wait(Until.findObject(By.res(TARGET_PACKAGE, emailTextViewResId)),
                        COMPONENT_TIMEOUT);

        // Make sure the field was found
        assertThat(emailTextView, notNullValue());

        // Fill the field with incorrect value
        emailTextView.setText("test");

        // The button resource identifier
        final String buttonViewResId = "email_sign_in_button";

        // Find "sign in" button and click
        UiObject2 signInButton = mDevice
                .wait(Until.findObject(By.res(TARGET_PACKAGE, buttonViewResId)),
                        COMPONENT_TIMEOUT);

        // Emulate Click
        signInButton.click();

        // At this point, an invalid email message should be displayed...

        // Find the field (again) and get its updated state
        UiObject2 emailTextViewWithError = mDevice
                .wait(Until.findObject(By.res(TARGET_PACKAGE, emailTextViewResId)),
                        COMPONENT_TIMEOUT);

        // Make sure the field was found
        assertThat(emailTextViewWithError, notNullValue());

        // However, setError() was introduced in recent version of Anrdoid SDK
        // and UIObject2 does not have an "easy and convenient way" to access to the mError
        // properties, so we'll use a "custom hack" here...
        String mInitialError = getErrorOfUIObject2(emailTextView);
        String mFinalError = getErrorOfUIObject2(emailTextViewWithError);

        // Verify initial and final error
        assertThat(mInitialError, nullValue());
        assertThat(mFinalError, is(equalTo("This email address is invalid")));

    }


    @Test
    public void testEmptyEmail() throws InterruptedException {

        // The button resource identifier
        final String buttonViewResId = "email_sign_in_button";

        // Find "sign in" button and click
        UiObject2 signInButton = mDevice
                .wait(Until.findObject(By.res(TARGET_PACKAGE, buttonViewResId)),
                        COMPONENT_TIMEOUT);

        // Emulate Click
        signInButton.click();

        // At this point, an invalid email message should be displayed...

        // The field resource identifier
        final String emailTextViewResId = "email";

        // Find the field (again) and get its updated state
        UiObject2 emailTextViewWithError = mDevice
                .wait(Until.findObject(By.res(TARGET_PACKAGE, emailTextViewResId)),
                        COMPONENT_TIMEOUT);

        // Make sure the field was found
        assertThat(emailTextViewWithError, notNullValue());

        // However, setError() was introduced in recent version of Anrdoid SDK
        // and UIObject2 does not have an "easy and convenient way" to access to the mError
        // properties, so we'll use a "custom hack" here...
        String mFinalError = getErrorOfUIObject2(emailTextViewWithError);

        // Verify initial and final error
        assertThat(mFinalError, is(equalTo("This field is required")));
    }

    @Test
    public void testEmptyPassword() throws InterruptedException {

        // The button resource identifier
        final String buttonViewResId = "email_sign_in_button";

        // Find "sign in" button and click
        UiObject2 signInButton = mDevice
                .wait(Until.findObject(By.res(TARGET_PACKAGE, buttonViewResId)),
                        COMPONENT_TIMEOUT);

        // Emulate Click
        signInButton.click();

        // At this point, an invalid email message should be displayed...

        // The field resource identifier
        final String passwordTextViewResId = "password";

        // Find the field (again) and get its updated state
        UiObject2 passwordTextViewWithError = mDevice
                .wait(Until.findObject(By.res(TARGET_PACKAGE, passwordTextViewResId)),
                        COMPONENT_TIMEOUT);

        // Make sure the field was found
        assertThat(passwordTextViewWithError, notNullValue());

        // However, setError() was introduced in recent version of Anrdoid SDK
        // and UIObject2 does not have an "easy and convenient way" to access to the mError
        // properties, so we'll use a "custom hack" here...
        String mFinalError = getErrorOfUIObject2(passwordTextViewWithError);

        // Verify initial and final error
        assertThat(mFinalError, is(equalTo("This password is too short")));
    }


    @Test
    public void testSuccessfulLogin() throws InterruptedException {

        // The field resource identifier
        final String emailTextViewResId = "email";

        // Find email field (and it's current state)
        UiObject2 emailTextView = mDevice
                .wait(Until.findObject(By.res(TARGET_PACKAGE, emailTextViewResId)),
                        COMPONENT_TIMEOUT);

        // Make sure the field was found
        assertThat(emailTextView, notNullValue());

        // Fill the field with incorrect value
        emailTextView.setText("pierre.henelle@gmail.com");

        final String passwordViewResId = "password";

        // Find the field (again) and get its updated state
        UiObject2 passwordTextView = mDevice
                .wait(Until.findObject(By.res(TARGET_PACKAGE, passwordViewResId)),
                        COMPONENT_TIMEOUT);

        // Make sure the field was found
        assertThat(passwordTextView, notNullValue());

        passwordTextView.setText("123456");

        // The button resource identifier
        final String buttonViewResId = "email_sign_in_button";

        // Find "sign in" button and click
        UiObject2 signInButton = mDevice
                .wait(Until.findObject(By.res(TARGET_PACKAGE, buttonViewResId)),
                        COMPONENT_TIMEOUT);

        assertThat(signInButton, notNullValue());

        // Emulate Click
        signInButton.clickAndWait(Until.newWindow(), LAUNCH_TIMEOUT);

        // At this point, login should be successful and new activity have appear
        // So we will search for its component on it
        final String content = "home_content";
        UiObject2 homeContentView = mDevice
                .wait(Until.findObject(By.res(TARGET_PACKAGE, content)),
                        LAUNCH_TIMEOUT);

        assertThat(homeContentView, notNullValue());
    }

}
