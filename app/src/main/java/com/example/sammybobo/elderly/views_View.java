package com.example.sammybobo.elderly;


import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Sammy bobo on 18/02/2016.
 */
public class views_View
{
    Button view;
    WifiManager wifiManager;

    public Button _wifi(String a, final Context b, int _num_id,final WifiManager c, BluetoothAdapter d){

        view = new Button(b);
        view.setText(a);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.wifi:c.setWifiEnabled(false);
                        break;
                    case R.id.mobile:
                        break;
                    case R.id.flash:
                        break;
                    case R.id.bluetooth:
                        break;
                    case R.id.wifi_on:c.setWifiEnabled(true);
                        break;
                    case R.id.mobile_on:
                        break;
                    case R.id.flash_on:
                        break;
                    case R.id.bluetooth_on:
                        break;
                    default:

                }


            }
        });
        return view;
    };



}

