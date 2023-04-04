package com.app.ac_panel.controllers;

import com.app.ac_panel.services.DeviceService;
import com.app.ac_panel.services.TreeSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TreeSetController {
    private final TreeSetService treeSetService;
    private final DeviceService deviceService;

    @GetMapping("/tree/build")
    public String buildHeaders() {
        if (treeSetService.createHeaders()) {
            return "result_ok";
        } else {
            return "result_not_ok";
        }
    }

    @GetMapping("/tree/build_desc")
    public String buildDesc() {
        treeSetService.addElements();
        return "result_ok";
    }
}
