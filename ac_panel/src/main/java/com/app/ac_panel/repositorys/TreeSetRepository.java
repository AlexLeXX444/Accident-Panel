package com.app.ac_panel.repositorys;

import com.app.ac_panel.models.treeset.TreeDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeSetRepository extends JpaRepository<TreeDevice, Long> {
}
