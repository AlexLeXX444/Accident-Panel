package com.app.ac_panel.services;

import com.app.ac_panel.models.Device;
import com.app.ac_panel.models.treeset.TreeDevice;
import com.app.ac_panel.repositorys.DeviceRepository;
import com.app.ac_panel.repositorys.TreeSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeSetService {
    private final TreeSetRepository treeSetRepository;
    private final DeviceRepository deviceRepository;

    public boolean createHeaders() {
        long counter = 0;
        List<Device> devicesToHeaders = deviceRepository.findByParentId(0L);
        if (devicesToHeaders.size() > 0) {
            for (Device device : devicesToHeaders) {
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
                counter++;
                treeDevice.setLeftKey(counter);
                counter++;
                treeDevice.setRightKey(counter);

                treeSetRepository.save(treeDevice);
            }
            return true;
        } else {
            return false;
        }
    }

    public void addElements() {
        List<Device> devices = deviceRepository.findByParentIdNot(0L);
        int counter = 1;

        while (counter > 0) {
            counter = 0;
            for (Device device : devices) {
                if (!treeSetRepository.existsById(device.getId())) {
                    if (addOneElement(device)) {
                        counter++;
                    }
                }
            }
        }
    }

    public boolean addOneElement(Device device) {
        TreeDevice parentDevice = treeSetRepository.findById(device.getParentId()); // находим родительский узел по ID
        if (parentDevice != null) {

            List<TreeDevice> greaterLeftKey = treeSetRepository.findByLeftKeyGreaterThan(parentDevice.getRightKey() - 1); // выбираем ВСЕ элементы у которых левый ключ выше чем у родительского
            for (TreeDevice treeDeviceLeftKey : greaterLeftKey) {
                treeDeviceLeftKey.setLeftKey(treeDeviceLeftKey.getLeftKey() + 2);
            }

            List<TreeDevice> greaterRightKey = treeSetRepository.findByRightKeyGreaterThan(parentDevice.getRightKey() - 1); // выбираем ВСЕ элементы у которых правый ключ выше чем у родительского
            for (TreeDevice treeDeviceRightKey : greaterRightKey) {
                treeDeviceRightKey.setRightKey(treeDeviceRightKey.getRightKey() + 2);
            }

            TreeDevice tDevice = new TreeDevice();
            tDevice.setId(device.getId());
            tDevice.setName(device.getName());
            tDevice.setHostId(device.getHostId());
            tDevice.setUsCode(device.getUsCode());
            tDevice.setNodeId(device.getNodeId());
            tDevice.setNodeName(device.getNodeName());
            tDevice.setType(device.getType());
            tDevice.setModel(device.getModel());
            tDevice.setIpAddress(device.getIpAddress());
            tDevice.setParentId(device.getParentId());
            tDevice.setTotalNumberOfLogins(device.getTotalNumberOfLogins());
            tDevice.setNumberOfLogins(device.getNumberOfLogins());
            tDevice.setLeftKey(parentDevice.getRightKey() - 2);
            tDevice.setRightKey(parentDevice.getRightKey() - 1);
            treeSetRepository.save(tDevice);

            return true;
        }

        return false;
    }

    public boolean removeOneElement(Device device) {
        TreeDevice removedDevice = treeSetRepository.findById(device.getId()).orElse(null);
        if (removedDevice != null) {

            return true;
        }
        return false;
    }
}
