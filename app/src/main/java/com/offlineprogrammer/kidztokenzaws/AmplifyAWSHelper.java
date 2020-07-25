package com.offlineprogrammer.kidztokenzaws;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Kid;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class AmplifyAWSHelper {

    Context mContext;


    public AmplifyAWSHelper(Context c){

        mContext = c;

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



}
