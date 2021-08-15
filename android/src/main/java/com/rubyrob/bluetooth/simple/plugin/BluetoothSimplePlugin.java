package com.rubyrob.bluetooth.simple.plugin;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.ActivityResult;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(name = "BluetoothSimple",
    permissions = {
        @Permission(
            alias = "bluetooth",
            strings = {
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.BLUETOOTH_PRIVILEGED,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            }
        ),
    }
)
public class BluetoothSimplePlugin extends Plugin {

    final private BluetoothSimple implementation = new BluetoothSimple();

    @PluginMethod
    public void setName(PluginCall call) {
        String result = implementation.setName(call.getString("name"));
        JSObject ret = new JSObject();
        ret.put("resolve", result);
        call.resolve(ret);
    }
    @PluginMethod()
    public void enable(PluginCall call) throws Exception {
        try{
            implementation.enable(call, this, "resultSetBluethooth");
        }catch (Exception e){
            JSObject ret = new JSObject();
            ret.put("Error", e.getMessage());
            call.resolve(ret);
        }
    }
    @ActivityCallback()
    private void resultSetBluethooth(PluginCall call, ActivityResult result) {
        JSObject ret = new JSObject();

        if (result.getResultCode() == Activity.RESULT_OK) {
            ret.put("resolve", "OK");
            call.resolve(ret);
        } else {
            ret.put("resolve", "KO");
            call.resolve(ret);
        }
    }
    @PluginMethod(returnType = PluginMethod.RETURN_CALLBACK)
    public void startDiscovery(PluginCall call) {
        call.setKeepAlive(true);
        requestAllPermissions(call,"startDiscoveryPermCallback");
    }
    @PermissionCallback()
    public void startDiscoveryPermCallback(PluginCall call){

        BroadcastReceiver receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                JSObject ret = new JSObject();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    String deviceName = device.getName();
                    String deviceHardwareAddress = device.getAddress(); // MAC address
                    ret.put("deviceName", deviceName);
                    ret.put("deviceHardwareAddress", deviceHardwareAddress);
                }else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                    ret.put("stateAction", "DISCOVERY_FINISHED");
                }
                call.resolve(ret);
            }
        };
        implementation.startDiscovery(receiver, this);
    }
    @PluginMethod()
    public void setDiscoverable(PluginCall call){
        implementation.setDiscoverable(call, this, "setDiscoverableCallback" );
    }
    @ActivityCallback()
    public void setDiscoverableCallback(PluginCall call, ActivityResult result){
        JSObject ret = new JSObject();
        ret.put("resolve", "OK");
        call.resolve(ret);
    }
}
