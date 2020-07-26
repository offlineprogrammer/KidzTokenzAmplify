package com.offlineprogrammer.kidztokenzaws;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthChannelEventName;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.InitializationStatus;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.hub.HubChannel;

public class KidzTokenz extends Application {
    private User m_User;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");

            Amplify.Hub.subscribe(HubChannel.AUTH,
                    hubEvent -> {
                        if (hubEvent.getName().equals(InitializationStatus.SUCCEEDED.toString())) {
                            Log.i("AuthQuickstart", "Auth successfully initialized");
                        } else if (hubEvent.getName().equals(InitializationStatus.FAILED.toString())) {
                            Log.i("AuthQuickstart", "Auth failed to succeed");
                        } else {
                            switch (AuthChannelEventName.valueOf(hubEvent.getName())) {
                                case SIGNED_IN:
                                    Log.i("AuthQuickstart", "Auth just became signed in.");
                                case SIGNED_OUT:
                                    Log.i("AuthQuickstart", "Auth just became signed out.");
                                case SESSION_EXPIRED:
                                    Log.i("AuthQuickstart", "Auth session just expired.");
                            }
                        }
                    }
            );

        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }
    }

    public User getUser() {
        return m_User;
    }

    public void setUser(User user) {
        this.m_User = user;
    }

}


