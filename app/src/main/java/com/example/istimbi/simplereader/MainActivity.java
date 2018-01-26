package com.example.istimbi.simplereader;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Formatter;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.DiscoveryListener;
import io.resourcepool.ssdp.model.DiscoveryRequest;
import io.resourcepool.ssdp.model.SsdpRequest;
import io.resourcepool.ssdp.model.SsdpService;
import io.resourcepool.ssdp.model.SsdpServiceAnnouncement;



public class MainActivity extends AppCompatActivity {
    TextView textView;
    private final static String DISCOVER_MESSAGE = "M-SEARCH * HTTP/1.1\r\n"
            + "HOST: 239.255.255.250:1900\r\n" + "MAN: \"ssdp:discover\"\r\n"
            + "MX: 3\r\n" + "ST: ssdp:all\r\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);






    }

    public void get(View view) {
        ConnectToServer connectToServer = new ConnectToServer();
        connectToServer.execute("https://gingoo.suroot.com/?action=newuser&email=test@timofey.isakov");

        try {
            String result = connectToServer.get();
            textView.setText(result);
            //Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void getip(View view) {
        String[] s = UPnPDiscovery.discoverDevices(MainActivity.this);
        Toast.makeText(MainActivity.this, s[0], Toast.LENGTH_SHORT).show();
    }
}
