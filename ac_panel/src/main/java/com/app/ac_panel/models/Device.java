package com.app.ac_panel.models;

import com.app.ac_panel.models.treeset.TreeDevice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "all_devices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    @Column(name = "dev_id", nullable = false)
    private Long id;

    @Column(name = "dev_name")
    private String name;

    @Column(name = "dev_host_id")
    private long hostId = 0;

    @Column(name = "dev_dev_us_code")
    private long usCode = 0;

    @Column(name = "dev_node_id")
    private long nodeId = 0;

    @Column(name = "dev_node_name")
    private String nodeName = "";

    @Column(name = "dev_type")
    private String type = "";

    @Column(name = "dev_model")
    private String model = "";

    @Column(name = "dev_ip_address")
    private String ipAddress = "0.0.0.0";

    @Column(name = "dev_parent_id")
    private long parentId = 0;

    @Column(name = "dev_totel_numbers_of_logins")
    private int totalNumberOfLogins = 0;

    @Column(name = "dev_number_of_logins")
    private int numberOfLogins = 0;
}
