package com.app.ac_panel.repositorys;

import com.app.ac_panel.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
