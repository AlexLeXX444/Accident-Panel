package com.app.ac_panel.controllers;

import com.app.ac_panel.services.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NodeController {
    private final NodeService nodeService;

    @GetMapping("/nodes/reload")
    public String reloadNodesFrom1C() {
        if (nodeService.saveNodesFrom1C()) {
            return "result_ok";
        }
        return "result_not_ok";
    }
}
