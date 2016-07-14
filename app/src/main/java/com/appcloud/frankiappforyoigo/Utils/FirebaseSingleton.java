package com.appcloud.frankiappforyoigo.Utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by cristian on 30/06/2016.
 */
public class FirebaseSingleton {

    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
                DatabaseReference ofertas = FirebaseDatabase.getInstance().getReference("ofertas_tacticas");
            ofertas.keepSynced(true);
        }
        return mDatabase;

    }
}
