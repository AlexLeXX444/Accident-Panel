package com.app.ac_panel.repositorys;

import com.app.ac_panel.models.treeset.TreeDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreeSetRepository extends JpaRepository<TreeDevice, Long> {
    TreeDevice findByParentId(long deviceParentId);

    List<TreeDevice> findByRightKeyGreaterThan(long rightKey);
}
