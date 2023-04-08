package com.app.ac_panel.repositorys;

import com.app.ac_panel.models.treeset.TreeDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreeDeviceRepository extends JpaRepository<TreeDevice, Long> {
    TreeDevice findByParentId(long deviceParentId);
    TreeDevice findById(long deviceId);

    List<TreeDevice> findByRightKeyGreaterThan(long rightKey);
    List<TreeDevice> findByLeftKeyGreaterThan(long leftKey);
}
