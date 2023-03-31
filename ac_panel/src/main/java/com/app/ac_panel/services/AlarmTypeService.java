package com.app.ac_panel.services;

import com.app.ac_panel.models.AlarmTypes;
import com.app.ac_panel.repositorys.AlarmTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AlarmTypeService {
    private final AlarmTypeRepository alarmTypeRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean saveAlarmTypesFrom1C() {
        String uriAlarmTypes1C = "http://server1c.freedom1.ru/UNF_CRM_WS/hs/Crash/alarmPanel?request=getAlarmTypes";
        ResponseEntity<AlarmTypes> response = restTemplate.getForEntity(uriAlarmTypes1C, AlarmTypes.class);
        try {
            alarmTypeRepository.saveAll(Objects.requireNonNull(response.getBody()).getAlarmTypes());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
