package com.app.ac_panel.services;

import com.app.ac_panel.models.Devices;
import com.app.ac_panel.repositorys.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean saveDevicesFrom1C() {
            String uriDevices1C = "http://server1c.freedom1.ru/UNF_CRM_WS/hs/Crash/alarmPanel?request=getDevices";
            ResponseEntity<Devices> response = restTemplate.getForEntity(uriDevices1C, Devices.class);
            try {
                deviceRepository.saveAll(Objects.requireNonNull(response.getBody()).getDevices());
                return true;
            } catch (Exception e) {
                return false;
            }
    }

}
