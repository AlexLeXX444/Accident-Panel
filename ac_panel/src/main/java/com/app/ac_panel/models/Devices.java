package com.app.ac_panel.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Devices {
    List<Device> devices;

    public Device getDeviceById(int id) {
        return devices.get(id);
    }
}
