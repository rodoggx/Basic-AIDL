package com.example.rodoggx.aidlbasic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by RodoggX on 8/7/2017.
 */

public class AddService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new IAddService.Stub() {
            public int add(int ValueFirst, int valueSecond) throws RemoteException {
                return (ValueFirst + valueSecond);
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
