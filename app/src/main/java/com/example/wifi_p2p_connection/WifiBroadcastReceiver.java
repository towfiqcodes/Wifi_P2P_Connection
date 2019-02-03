package com.example.wifi_p2p_connection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

public class WifiBroadcastReceiver extends BroadcastReceiver {
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel mChannel;
    private  MainActivity mainActivity;

    public WifiBroadcastReceiver(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel mChannel, MainActivity mainActivity) {
        this.wifiP2pManager = wifiP2pManager;
        this.mChannel = mChannel;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String myAction=intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(myAction)){
            //checked to see if Wi-Fi is enabled and notify appropriateactivity;
            int state=intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE,-1);

            if(state==WifiP2pManager.WIFI_P2P_STATE_ENABLED){
                Toast.makeText(context,"Wifi is ON",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"Wifi is OFF",Toast.LENGTH_SHORT).show();
            }
        }else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(myAction)){
            //Call WifiP2pManager.requestPeers() to get a list of current users
            if (wifiP2pManager !=null){
                wifiP2pManager.requestPeers(mChannel,mainActivity.peerListListener);
            }


        }else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(myAction)){
            //Respond to new connection or disconnections
        }else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(myAction)){
            //Respond to this device's Wi-Fi state Changing
        }

    }
}
