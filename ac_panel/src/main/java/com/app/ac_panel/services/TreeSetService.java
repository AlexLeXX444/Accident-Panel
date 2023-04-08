package com.app.ac_panel.services;

import com.app.ac_panel.models.Device;
import com.app.ac_panel.models.treeset.TreeDevice;
import com.app.ac_panel.repositorys.DeviceRepository;
import com.app.ac_panel.repositorys.TreeDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreeSetService {
    private final TreeDeviceRepository treeDeviceRepository;
    private final DeviceRepository deviceRepository;

    public boolean createHeaders() {
        long counter = 0;
        List<Device> devicesToHeaders = deviceRepository.findByParentId(0L);
        if (devicesToHeaders.size() > 0) {
            for (Device device : devicesToHeaders) {
                TreeDevice treeDevice = DeviceConverter.getTreeDevice(device);
                counter++;
                treeDevice.setLeftKey(counter);
                counter++;
                treeDevice.setRightKey(counter);

                treeDeviceRepository.save(treeDevice);
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
                if (!treeDeviceRepository.existsById(device.getId())) {
                    if (addOneElement(device)) {
                        counter++;
                    }
                }
            }
        }
    }

    public boolean addOneElement(Device device) {
        TreeDevice parentDevice = treeDeviceRepository.findById(device.getParentId()); // находим родительский узел по ID
        if (parentDevice != null) {

            List<TreeDevice> greaterLeftKey = treeDeviceRepository.findByLeftKeyGreaterThan(parentDevice.getRightKey() - 1); // выбираем ВСЕ элементы у которых левый ключ выше чем у родительского
            for (TreeDevice treeDeviceLeftKey : greaterLeftKey) {
                treeDeviceLeftKey.setLeftKey(treeDeviceLeftKey.getLeftKey() + 2);
            }

            List<TreeDevice> greaterRightKey = treeDeviceRepository.findByRightKeyGreaterThan(parentDevice.getRightKey() - 1); // выбираем ВСЕ элементы у которых правый ключ выше чем у родительского
            for (TreeDevice treeDeviceRightKey : greaterRightKey) {
                treeDeviceRightKey.setRightKey(treeDeviceRightKey.getRightKey() + 2);
            }

            TreeDevice tDevice = DeviceConverter.getTreeDevice(device);
            tDevice.setLeftKey(parentDevice.getRightKey() - 2);
            tDevice.setRightKey(parentDevice.getRightKey() - 1);
            treeDeviceRepository.save(tDevice);

            return true;
        }

        return false;
    }

    public boolean removeOneElement(Device device) {
        TreeDevice treeDevice = treeDeviceRepository.findById(device.getId()).orElse(null);
        if (treeDevice != null) {

            List<TreeDevice> greaterLeftKey = treeDeviceRepository.findByLeftKeyGreaterThan(treeDevice.getLeftKey()); // выбираем ВСЕ элементы у которых левый ключ выше чем у родительского
            for (TreeDevice treeDeviceLeftKey : greaterLeftKey) {
                treeDeviceLeftKey.setLeftKey(treeDeviceLeftKey.getLeftKey() - 2);
            }

            List<TreeDevice> greaterRightKey = treeDeviceRepository.findByRightKeyGreaterThan(treeDevice.getRightKey()); // выбираем ВСЕ элементы у которых правый ключ выше чем у родительского
            for (TreeDevice treeDeviceRightKey : greaterRightKey) {
                treeDeviceRightKey.setRightKey(treeDeviceRightKey.getRightKey() - 2);
            }

            treeDeviceRepository.deleteById(treeDevice.getId());
            return true;
        }
        return false;
    }

    public List<TreeDevice> returnFollowers(TreeDevice treeDevice) {
        List<TreeDevice> followersTreeDevices = new ArrayList<TreeDevice>();
        followersTreeDevices.add(treeDevice);

        if ((treeDevice.getRightKey() - treeDevice.getLeftKey()) > 1 ) {
            followersTreeDevices = treeDeviceRepository.findByLeftKeyGreaterThan(treeDevice.getLeftKey());
            followersTreeDevices.removeIf(s -> s.getRightKey() > treeDevice.getRightKey());
            return followersTreeDevices;
        }

        return new ArrayList<TreeDevice>();
    }
}
