package com.app.ac_panel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alarm_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmType {
    @Id
    @Column(name = "at_id", nullable = false)
    private long id;

    @Column(name = "at_name")
    private String name;

    @Column(name = "at_comment")
    private String comment;

    @Column(name = "at_path")
    private String path;
}
