package com.app.ac_panel.controllers;

import com.app.ac_panel.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final DeviceService deviceService;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
}
