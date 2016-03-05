package com.example.sammybobo.elderly;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sammybobo.elderly.views_View;

/**
 * Created by Sammy bobo on 13/02/2016.
 */
public class mainClass extends Activity
{
    TextView battery_stat;
    TextView other_not;
    int batLevel;
    String batlev;
    LinearLayout linearLayout;
    Button _wifi;
    Button _mobile;
    Button _bluetooth;
    Button _flash;
    WifiManager wifiManager;

    Button _wifi_on;
    Button _mobile_on;
    Button _bluetooth_on;
    Button _flash_on;
    BroadcastReceiver batteryStatus = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            batLevel = intent.getIntExtra("level", 0);
            battery_stat.append(String.valueOf(batLevel) + "%");
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == RESULT_OK) {
                Toast.makeText(mainClass.this, "Bluetooth turned on", Toast.LENGTH_LONG).show();
            }
            else if (resultCode == RESULT_CANCELED){
                Toast.makeText(mainClass.this, "Bluetooth not turned on. Try again", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        BluetoothAdapter b_adapter = BluetoothAdapter.getDefaultAdapter();

        views_View n = new views_View();
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout)findViewById(R.id.layout_button);
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        battery_stat = (TextView)findViewById(R.id.batteryStat);
        other_not = (TextView)findViewById(R.id.notif);



        _mobile = (Button)n._wifi("Turn off Mobile Network", this, 2, wifiManager, b_adapter);
        _mobile.setId(R.id.mobile);

        _bluetooth = (Button)n._wifi("Turn Bluetooth off", this, 2, wifiManager, b_adapter);
        _bluetooth.setId(R.id.bluetooth);

        _bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter.getDefaultAdapter().disable();
            }
        });

        _flash = (Button)n._wifi("Turn Flash light off", this, 3, wifiManager, b_adapter);
        _flash.setId(R.id.flash);

        _wifi = (Button)n._wifi("Turn Wifi off", this, 1, wifiManager, b_adapter);
        _wifi.setId(R.id.wifi);

        _mobile_on = (Button)n._wifi("Turn on Mobile Network", this, 2, wifiManager, b_adapter);
        _mobile_on.setId(R.id.mobile_on);

        _bluetooth_on = (Button)n._wifi("Turn on Bluetooth", this, 2, wifiManager, b_adapter);
        _bluetooth_on.setId(R.id.bluetooth_on);

        _bluetooth_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter.getDefaultAdapter().enable();
                BluetoothAdapter.getDefaultAdapter().disable();
                //Intent ab = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                //startActivityForResult(ab, 1);


            }
        });


        _flash_on = (Button)n._wifi("Turn Flash light on", this, 3, wifiManager, b_adapter);
        _flash_on.setId(R.id.flash_on);

        _wifi_on = (Button)n._wifi("Turn Wifi on", this, 1, wifiManager, b_adapter);
        _wifi_on.setId(R.id.wifi_on);

        linearLayout.addView(_flash);
        linearLayout.addView(_flash_on);

        if (wifiManager.isWifiEnabled())
        {
            battery_stat.append("Wifi is enabled");
            linearLayout.addView(_wifi);

        }
        else
        {
           other_not.append("Wifi is not enabled");
            linearLayout.addView(_wifi_on);
        }

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo.isAvailable()) {
                other_not.append("Network is on and working");
                linearLayout.addView(_mobile_on);
            } else {
                other_not.append("Network is off");
                linearLayout.addView(_mobile);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        if (b_adapter == null){
            other_not.append("Your device does not support bluetooth");

        }
        if (b_adapter.isEnabled())
        {
        other_not.append("Bluetooth Enabled");
            linearLayout.addView(_bluetooth);
        }
        else {
            other_not.append("Bluetooth is not enabled");
            linearLayout.addView(_bluetooth_on);
        }
        this.registerReceiver(this.batteryStatus, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }


}
