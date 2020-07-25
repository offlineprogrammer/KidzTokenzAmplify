package com.offlineprogrammer.kidztokenzaws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amplifyframework.auth.AuthProvider;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent.getData() != null && "kidztokenz".equals(intent.getData().getScheme())) {
            Amplify.Auth.handleWebUISignInResponse(intent);
        }
    }


    @Override
    public void onStart() {
        super.onStart();



        Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i("AmplifySignedIn", result.toString());

                    AuthUser oUser =  Amplify.Auth.getCurrentUser();
                    Log.i(TAG, "onStart:::: " + oUser.toString());
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    switch(cognitoAuthSession.getIdentityId().getType()) {
                        case SUCCESS:
                            Log.i("AmplifySignedIn", "onStart::::    IdentityId: " + cognitoAuthSession.toString());
                            Log.i("AmplifySignedIn", "IdentityId: " + cognitoAuthSession.getIdentityId().getValue());
                            break;
                        case FAILURE:
                            Log.i("AmplifySignedIn", "IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());
                    }


                } ,
                error -> {
                    Log.e("AmplifyQuickstart", error.toString());
                    Amplify.Auth.signInWithSocialWebUI(
                            AuthProvider.amazon(),
                            this,
                            result -> Log.i("AuthQuickstart", result.toString()),
                            error2 -> Log.e("AuthQuickstart", error2.toString())
                    );

                }
        );



    }

    private void getUserData() {


    }





}