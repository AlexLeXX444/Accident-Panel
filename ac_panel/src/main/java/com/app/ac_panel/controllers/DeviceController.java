package com.app.ac_panel.controllers;

import com.app.ac_panel.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping("/devices/reload")
    public String reloadDevicesFrom1C() {
        if (deviceService.saveDevicesFrom1C()) {
            return "result_ok";
        }
        return "result_not_ok";
    }
}
