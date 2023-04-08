package com.app.ac_panel.controllers;

import com.app.ac_panel.services.TreeDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class TreeSetController {
    private final TreeDeviceService treeDeviceService;

    @GetMapping("/tree/build")
    public String buildHeaders() {
        if (treeDeviceService.createHeaders()) {
            return "result_ok";
        } else {
            return "result_not_ok";
        }
    }

    @GetMapping("/tree/build_desc")
    public String buildDesc() {
        treeDeviceService.addElements();
        return "result_ok";
    }

    @GetMapping("/tree/remove/{id}")
    public String removeDevices(@PathVariable long id) {
        try {
            if (treeDeviceService.removeElements(id)) {
                return "result_ok";
            } else {
                return "result_not_ok";
            }
        } catch (Exception e) {
            return "result_not_ok";
        }
    }
}
