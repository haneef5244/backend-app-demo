package com.berjaya.demoappapi.dao;

import com.berjaya.demoappapi.entity.Exception;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionDAO extends JpaRepository<Exception, Long> {
}
