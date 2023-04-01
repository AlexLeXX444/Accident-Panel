package com.app.ac_panel.controllers;

import com.app.ac_panel.services.TreeSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TreeSetController {
    private final TreeSetService treeSetService;

    @GetMapping("/tree/build")
    public String buildHeaders() {
        if (treeSetService.createHeaders()) {
            return "result_ok";
        } else {
            return "result_not_ok";
        }
    }
}
