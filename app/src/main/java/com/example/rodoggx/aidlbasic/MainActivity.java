package com.example.rodoggx.aidlbasic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    IAddService service;
    AddServiceConnection connection;

    class AddServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = IAddService.Stub.asInterface((IBinder) iBinder);
            Toast.makeText(MainActivity.this, "AIDL Service connected", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            service = null;
            Toast.makeText(MainActivity.this, "AIDL Service connected", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
    }

    private void initService() {
        connection = new AddServiceConnection();
        Intent i = new Intent();
        i.setClassName("com.example.rodoggx.aidlbasic", com.example.rodoggx.aidlbasic.AddService.class.getName());
        boolean ret = bindService(i, connection, Context.BIND_AUTO_CREATE);
    }

    public void doMagic(View view) {
        TextView title = (TextView) findViewById(R.id.tv_title);
        EditText value1 = (EditText) findViewById(R.id.et_main_1);
        EditText value2 = (EditText) findViewById(R.id.et_main_2);
        EditText result = (EditText) findViewById(R.id.et_main_result);

        int et1 = 0, et2 = 0, res = -1;
        et1 = Integer.parseInt(value1.getText().toString());
        et2 = Integer.parseInt(value2.getText().toString());
        try {
            res = service.add(et1, et2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        result.setText(new Integer(res).toString());
    }

    private void releaseService() {
        unbindService(connection);
        connection = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseService();
    }
}
