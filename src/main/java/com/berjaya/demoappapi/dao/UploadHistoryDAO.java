package com.berjaya.demoappapi.dao;

import com.berjaya.demoappapi.entity.UploadHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadHistoryDAO extends JpaRepository<UploadHistory, Long> {
}
