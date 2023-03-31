package com.app.ac_panel.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nodes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    @Id
    @Column(name = "node_id", nullable = false)
    private long id;

    @Column(name = "node_name")
    private String name;

    @Column(name = "node_battery_capacity")
    private int batteryCapacity = 0;

    @Column(name = "node_total_number_of_logins")
    private int totalNumberOfLogins;

    @Column(name = "node_number_of_logins")
    private int numberOfLogins;

    @Column(name = "node_group")
    private String group;
}
