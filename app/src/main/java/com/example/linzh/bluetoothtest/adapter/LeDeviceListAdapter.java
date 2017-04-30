package com.example.linzh.bluetoothtest.adapter;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linzh.bluetoothtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzh on 2017/4/12.
 */

public class LeDeviceListAdapter extends RecyclerView.Adapter<LeDeviceListAdapter.ViewHolder> {

    private List<BluetoothDevice> mLeDeviceList;

    private OnItemClickListener onItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View deviceView;
        TextView leDeviceAddress;
        TextView leDeviceName;

        public ViewHolder(View view) {
            super(view);
            deviceView = view;
            leDeviceAddress = (TextView) view.findViewById(R.id.le_device_address);
            leDeviceName = (TextView)view.findViewById(R.id.le_device_name);
        }
    }

    public LeDeviceListAdapter() {
        mLeDeviceList = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {//屏幕滚动动态更新view
        BluetoothDevice leDevice = mLeDeviceList.get(position);
        holder.leDeviceAddress.setText(leDevice.getAddress());

        final String deviceName = mLeDeviceList.get(position).getName();
        if (!TextUtils.isEmpty(deviceName))
            holder.leDeviceName.setText(leDevice.getName());
        else
            holder.leDeviceName.setText(R.string.unknown_device);

        if (onItemClickListener != null) {
            holder.deviceView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mLeDeviceList.size();
    }

    /**
     * 添加可用蓝牙设备
     * @param device
     */
    public void addDevice(BluetoothDevice device) {
        if (!mLeDeviceList.contains(device)) {
            mLeDeviceList.add(device);
        }
    }

    public BluetoothDevice getDevice(int position) {
        return mLeDeviceList.get(position);
    }
    
    public Object getItem(int position) {
        return mLeDeviceList.get(position);
    }

    public void clear() {
        mLeDeviceList.clear();
    }

    public interface OnItemClickListener {
        void onItemClick(View view ,int position);
    }
}
