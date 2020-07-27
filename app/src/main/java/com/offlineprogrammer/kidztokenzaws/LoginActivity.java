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
import com.amplifyframework.datastore.generated.model.User;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    ImageButton login_with_amazon;
    ProgressBar log_in_progress;
    Boolean isSignIn_Initializeds = false;
    AmplifyAWSHelper amplifyAWSHelper;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_with_amazon = findViewById(R.id.login_with_amazon);
        log_in_progress = findViewById(R.id.log_in_progress);
        amplifyAWSHelper = new AmplifyAWSHelper(getApplicationContext());

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
                    getUserData();
                    //launchMainActivity();
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
                            getUserData();
                            //launchMainActivity();
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
        amplifyAWSHelper.getUserData().observeOn(Schedulers.io())
                //.observeOn(Schedulers.m)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext: " + user.getUserId());
                        Log.d(TAG, "onNext: m_User " + amplifyAWSHelper.kidzTokenz.getUser().getUserEmail());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                launchMainActivity();
                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e != null) {

                            Log.e(TAG, "onError: " + e.getMessage());

                        }


                        saveUser();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });

    }

    private void saveUser() {
        amplifyAWSHelper.saveUser().observeOn(Schedulers.io())
                //.observeOn(Schedulers.m)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext: " + user.getUserId());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                launchMainActivity();
                            }
                        });


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    private void launchMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }


}