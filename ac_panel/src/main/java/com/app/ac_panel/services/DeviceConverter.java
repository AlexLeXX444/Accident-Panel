package com.app.ac_panel.services;

import com.app.ac_panel.models.Device;
import com.app.ac_panel.models.treeset.TreeDevice;

public class DeviceConverter {

    public static TreeDevice getTreeDevice(Device device) {
        TreeDevice treeDevice = new TreeDevice();

        treeDevice.setId(device.getId());
        treeDevice.setName(device.getName());
        treeDevice.setHostId(device.getHostId());
        treeDevice.setUsCode(device.getUsCode());
        treeDevice.setNodeId(device.getNodeId());
        treeDevice.setNodeName(device.getNodeName());
        treeDevice.setType(device.getType());
        treeDevice.setModel(device.getModel());
        treeDevice.setIpAddress(device.getIpAddress());
        treeDevice.setParentId(device.getParentId());
        treeDevice.setTotalNumberOfLogins(device.getTotalNumberOfLogins());
        treeDevice.setNumberOfLogins(device.getNumberOfLogins());
        treeDevice.setLeftKey(0);
        treeDevice.setRightKey(0);

        return treeDevice;
    }
}
