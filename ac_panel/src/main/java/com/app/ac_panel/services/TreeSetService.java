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
        List<TreeDevice> treeDevices = treeSetRepository.findAll();
        int counter = 1;
        while (counter > 0) {
            counter = 0;

            for (Device device : devices) {
                long deviceParentId = device.getParentId();
                TreeDevice treeDevice = treeSetRepository.findByParentId(deviceParentId); // находим родительский элемент в дереве
                List<TreeDevice> greaterRightKey = treeSetRepository.findByRightKeyGreaterThan(treeDevice.getRightKey()); // выбираем ВСЕ элементы у которых правый и левый ключи выше чем у родительского
                devices.remove(device);
                counter++;
            }
        }
    }
}
