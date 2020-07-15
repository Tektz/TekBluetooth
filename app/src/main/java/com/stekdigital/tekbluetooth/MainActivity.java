package com.stekdigital.tekbluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button btn_o,btn_v,btn_l,btn_of;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_o =(Button) findViewById(R.id.btn_on);
        btn_v =(Button) findViewById(R.id.btn_visible);
        btn_l =(Button) findViewById(R.id.btn_list);
        btn_of =(Button) findViewById(R.id.btn_off);

        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView)findViewById(R.id.listView);
    }

    public void on(View v){
       if(!BA.isEnabled()){
           Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
           startActivityForResult(turnOn, 0);
           Toast.makeText(this, "Turn on", Toast.LENGTH_LONG).show();
       }
       else{
           Toast.makeText(this, "Already on", Toast.LENGTH_LONG).show();
       }
    }

    public void off(View v){
        BA.disable();
        Toast.makeText(this, "Turned off", Toast.LENGTH_LONG).show();
        
    }

    public void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

    public void list(View v){
        pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();

        for(BluetoothDevice bt : pairedDevices) list.add(bt.getName());
        Toast.makeText(this, "Showing Paired Devices",Toast.LENGTH_SHORT).show();
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
    }
}