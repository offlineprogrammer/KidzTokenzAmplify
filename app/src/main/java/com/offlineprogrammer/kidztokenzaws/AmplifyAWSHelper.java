package com.offlineprogrammer.kidztokenzaws;

import android.content.Context;
import android.util.Log;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.generated.model.Kid;
import com.amplifyframework.datastore.generated.model.User;

import java.util.Calendar;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class AmplifyAWSHelper {
    private static final String TAG = "AmplifyAWSHelper";

    Context mContext;
    KidzTokenz kidzTokenz;


    public AmplifyAWSHelper(Context c){

        mContext = c;
        kidzTokenz = (KidzTokenz) c;
        Amplify.DataStore.observe(Kid.class,
                started -> Log.i("Tutorial", "Observation began."),
                change -> Log.i("Tutorial", change.item().toString()),
                failure -> Log.e("Tutorial", "Observation failed.", failure),
                () -> Log.i("Tutorial", "Observation complete.")
        );

    }


    public Observable<Kid> saveKid(Kid newKid) {
        return Observable.create((ObservableEmitter<Kid> emitter) -> {
            Amplify.DataStore.save(
                    newKid,
                    success -> {
                        Log.i("Tutorial", "Saved item: " + success.item().getKidName());
                        emitter.onNext(newKid);

                    },
                    error -> {
                        Log.e("Tutorial", "Could not save item to DataStore", error);
                        emitter.onError(error);
                    }
            );
        });
    }


    Observable<User> saveUser() {
        return Observable.create((ObservableEmitter<User> emitter) -> {
            Date currentTime = Calendar.getInstance().getTime();
            AuthUser currentUser = Amplify.Auth.getCurrentUser();
            User newUser = User.builder()
                    .dateCreated(currentTime.toString())
                    .userEmail("test")
                    .userId(currentUser.getUserId())
                    .build();
            Amplify.DataStore.save(
                    newUser,
                    success -> {
                        Log.i("Tutorial", "Saved item: " + success.item().getUserId());
                        emitter.onNext(newUser);
                        kidzTokenz.setUser(newUser);

                    },
                    error -> {
                        Log.e("Tutorial", "Could not save item to DataStore", error);
                        emitter.onError(error);
                    }
            );

        });
    }


    Observable<User> getUserData() {
        return Observable.create((ObservableEmitter<User> emitter) -> {
            AuthUser currentUser = Amplify.Auth.getCurrentUser();
            Amplify.DataStore.query(User.class, Where.matches(User.USER_ID.gt(currentUser.getUserId())),
                    users -> {
                        if (users.hasNext()) {
                            User user = users.next();
                            kidzTokenz.setUser(user);
                            emitter.onNext(kidzTokenz.getUser());
                            Log.i("MyAmplifyApp", "User: " + user);
                        } else {
                            emitter.onError(null);
                        }
                    },
                    failure -> {
                        Log.e("MyAmplifyApp", "Query failed.", failure);
                        emitter.onError(failure);

                    }
            );
        });
    }


}
