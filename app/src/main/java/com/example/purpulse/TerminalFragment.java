package com.example.purpulse;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.IBinder;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TerminalFragment extends Fragment implements ServiceConnection,SerialListener {

    private enum Connected {False, Pending, True}
    private String deviceAdress;
    private SerialService service;
    private TextView receiveText;
    private Connected connected = Connected.False;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceAdress = getArguments().getString("device");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terminal, container, false);
        receiveText = view.findViewById(R.id.receive_text);
        receiveText.setTextColor(getResources().getColor(R.color.blue));
        receiveText.setMovementMethod(ScrollingMovementMethod.getInstance());
        return view;
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }

    /**Serial**/
    private void connect(){
        try{
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = adapter.getRemoteDevice(deviceAdress);
            status("連接中......");
            connected = Connected.Pending;
            SerialSocket socket = new SerialSocket(getActivity().getApplicationContext(), device);
            service.connect(socket);
        }catch (Exception e){
            onSerialConnectError(e);
        }
    }

    private void disconnect(){
        connected = Connected.False;
        service.disconnect();
    }

    private void status(String str) {
        SpannableStringBuilder spn = new SpannableStringBuilder(str + '\n');
        spn.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)), 0, spn.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        receiveText.append(spn);
    }

    /**SerialListener**/
    @Override
    public void onSerialConnect() {
        status("已連接");
        connected = Connected.True;
    }

    @Override
    public void onSerialConnectError(Exception e) {
        status("連接失敗：" + e.getMessage());
        disconnect();
    }

    @Override
    public void onSerialRead(byte[] data) {

    }

    @Override
    public void onSerialIoError(Exception e) {
        status("連接中斷：" + e.getMessage());
        disconnect();
    }
}