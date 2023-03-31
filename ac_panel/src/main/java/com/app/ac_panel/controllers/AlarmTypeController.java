package com.app.ac_panel.controllers;

import com.app.ac_panel.services.AlarmTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AlarmTypeController {
    private final AlarmTypeService alarmTypeService;

    @GetMapping("/alarm_types/reload")
    public String reloadAlarmTypesFrom1C() {
        if (alarmTypeService.saveAlarmTypesFrom1C()) {
            return "result_ok";
        }
        return "result_not_ok";
    }
}
