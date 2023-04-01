package com.app.ac_panel.repositorys;

import com.app.ac_panel.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByParentId(Long parentId);
    List<Device> findByParentIdNot(Long parentId);
}
