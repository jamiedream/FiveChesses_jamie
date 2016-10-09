package chunying.fivechesses_v2;

/**
 * Created by Juyn-Ying on 3/10/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class LoginFB extends FragmentActivity {
    private LoginButton login;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    //Facebook login button
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Profile profile = Profile.getCurrentProfile();
            nextActivity(profile);
        }
        @Override
        public void onCancel() {        }
        @Override
        public void onError(FacebookException e) {      }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_battle);
        login = (LoginButton) findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        FacebookLogin();


    }

    @Nullable
//    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash, container, false);

        login.setReadPermissions("email");
        // If using in a fragment
//        login.setFragment(this);
        // Other app specific specialization

        // Callback registmration
        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
        return view;
    }

    public void FacebookLogin(){
        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();    }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };
        login.setReadPermissions("user_friends");
        login.registerCallback(callbackManager, callback);


//        login.setReadPermissions("email");
//        login.setReadPermissions("public_profile");
//        login.setReadPermissions("user_friends");




    }


    public void random(View v) {
//        Intent it = new Intent(this, Random.class);
//        startActivity(it);
    }


    public void find(View v) {
//        Intent it = new Intent(this, Find.class);
//        startActivity(it);
    }

    public void show(View v) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        Music.play(this, R.raw.music);
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }
    @Override
    protected void onPause() {
        super.onPause();
        Music.stop(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
        finish();
        startActivity(getIntent());
    }
    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        callbackManager.onActivityResult(requestCode, responseCode, intent);

    }

    private void nextActivity(Profile profile){
        if(profile != null){
            Intent main = new Intent(LoginFB.this, BattleActivity.class);
            main.putExtra("name", profile.getFirstName());
            main.putExtra("surname", profile.getLastName());
            main.putExtra("imageUrl", profile.getProfilePictureUri(20,20).toString());
            startActivity(main);
        }
    }
}
