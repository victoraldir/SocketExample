package com.quartzodev.socketexample;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

/**
 * Created by victoraldir on 09/07/2017.
 */

public class SocketService extends IntentService {

    public static String ACTION_CONNECT = "connect";
    public static String ACTION_DISCONNECT = "disconnect";
    public static String ACTION_START_SERVER = "start-server";
    public static String ACTION_STOP_SERVER = "stop-server";

    public SocketService() {
        super(SocketService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if(intent != null){

            if(intent.getAction().equals(ACTION_CONNECT)){

                sendServerStatus(ACTION_CONNECT,intent.getData().toString());

            }

        }

    }

    private void sendServerStatus(String action, String host){

        String msg = "";

        if(action.equals(ACTION_CONNECT)){
            msg = "Connect to: " + host;
        }else if(action.equals(ACTION_DISCONNECT)){
            msg = "Disconnected from: " + host;
        }

        Intent localIntent = new Intent(Constants.BROADCAST_ACTION)
                .putExtra(Constants.EXTENDED_SERVER_STATUS,
                        msg);

        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
