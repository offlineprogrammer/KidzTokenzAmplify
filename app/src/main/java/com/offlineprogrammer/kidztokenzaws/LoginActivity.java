package com.offlineprogrammer.kidztokenzaws;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.core.Amplify;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    ImageButton login_with_amazon;
    ProgressBar log_in_progress;
    Boolean isSignIn_Initializeds = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_with_amazon = findViewById(R.id.login_with_amazon);
        log_in_progress = findViewById(R.id.log_in_progress);

        login_with_amazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log_in_progress.setVisibility(View.VISIBLE);
                login_with_amazon.setVisibility(View.GONE);
                isSignIn_Initializeds = true;
                login();
            }
        });


    }

    private void login() {
        Amplify.Auth.signInWithSocialWebUI(
                AuthProvider.amazon(),
                this,
                result -> {
                    Log.i(TAG, "AuthQuickstart RESULT " + result.toString());
                    isSignIn_Initializeds = false;
                    launchMainActivity();
                },

                error2 -> {
                    Log.e(TAG, "AuthQuickstart ERROR " + error2.toString());
                    isSignIn_Initializeds = false;
                }
        );

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (intent.getData() != null && "kidztokenz".equals(intent.getData().getScheme())) {
            Amplify.Auth.handleWebUISignInResponse(intent);
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        if (isSignIn_Initializeds) return;
        log_in_progress.setVisibility(View.VISIBLE);
        login_with_amazon.setVisibility(View.GONE);


        Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i(TAG, "AmplifySignedIn SESSION " + result.toString());
//                    AuthUser oUser =  Amplify.Auth.getCurrentUser();
                    //                   Log.i(TAG, "onStart:::: " + oUser.toString());
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    switch (cognitoAuthSession.getIdentityId().getType()) {
                        case SUCCESS:
                            Log.i(TAG, "AmplifySignedIn onStart::::    IdentityId: " + cognitoAuthSession.toString());
                            Log.i(TAG, "AmplifySignedInIdentityId: " + cognitoAuthSession.getIdentityId().getValue());
                            launchMainActivity();
                            break;
                        case FAILURE:
                            enableLogin();
                            Log.i(TAG, "AmplifySignedIn IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());
                    }
                },
                error -> {
                    Log.i(TAG, "onStart: NOT SIGNED IN");
                    enableLogin();
                    Log.e(TAG, "AmplifyQuickstart ERROR " + error.toString());
                }
        );
    }

    private void enableLogin() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                log_in_progress.setVisibility(View.GONE);
                login_with_amazon.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getUserData() {


    }

    private void launchMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }


}