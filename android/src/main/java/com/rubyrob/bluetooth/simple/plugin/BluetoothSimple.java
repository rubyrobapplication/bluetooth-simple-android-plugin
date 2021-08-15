package com.rubyrob.bluetooth.simple.plugin;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import com.getcapacitor.PluginCall;

public class BluetoothSimple {

    final private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public String setName(String name){
        boolean result = bluetoothAdapter.setName(name);
        return result ? "OK" : "KO";
    }
    public void enable(PluginCall call, BluetoothSimplePlugin plugin, String activityCallback) throws Exception {
        if (bluetoothAdapter == null) {
            throw (new Exception("Device doesn't support Bluetooth"));
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            plugin.startActivityForResult(call, enableBtIntent, activityCallback);
        }
    }
    public void startDiscovery(BroadcastReceiver receiver, BluetoothSimplePlugin plugin ){
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        plugin.getActivity().registerReceiver(receiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        plugin.getActivity().registerReceiver(receiver, filter);
        bluetoothAdapter.startDiscovery();
    }
    public void setDiscoverable(PluginCall call, BluetoothSimplePlugin plugin,  String activityCallback){
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        enableBtIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
        plugin.startActivityForResult(call, enableBtIntent, activityCallback);
    }
}
