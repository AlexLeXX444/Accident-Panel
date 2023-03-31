package com.app.ac_panel.services;

import com.app.ac_panel.models.Nodes;
import com.app.ac_panel.repositorys.NodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NodeService {
    private final NodeRepository nodeRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean saveNodesFrom1C() {
        String uriNodes1C = "http://server1c.freedom1.ru/UNF_CRM_WS/hs/Crash/alarmPanel?request=getNodes";
        ResponseEntity<Nodes> response = restTemplate.getForEntity(uriNodes1C, Nodes.class);
        try {
            nodeRepository.saveAll(Objects.requireNonNull(response.getBody()).getNodes());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
