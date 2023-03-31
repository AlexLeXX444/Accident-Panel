package com.app.ac_panel.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AlarmTypes {
    private List<AlarmType> alarmTypes;

    public AlarmType getAlarmTypeById(int id) {
        return alarmTypes.get(id);
    }
}
