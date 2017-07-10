package com.quartzodev.socketexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private StatusReceiver mStatusReceiver;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mStatusReceiver = new StatusReceiver();
    }


    public void connect(View view){

        Intent intent = new Intent(this,SocketService.class);
        intent.setAction(SocketService.ACTION_CONNECT);
        intent.setData(Uri.parse("www.blabla.com"));
        startService(intent);
    }

    public void startServer(View view){

        Intent intent = new Intent(this,SocketService.class);
        intent.setAction(SocketService.ACTION_CONNECT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mStatusReceiver,
                new IntentFilter(Constants.BROADCAST_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mStatusReceiver);
    }

    public class StatusReceiver extends BroadcastReceiver {

        private StatusReceiver(){}

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Constants.BROADCAST_ACTION)){
                Toast.makeText(mContext,
                        "Connect to: " + intent.getExtras().getString(Constants.EXTENDED_SERVER_STATUS),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
