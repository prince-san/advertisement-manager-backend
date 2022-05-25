package com.princesan.advertisementmanagerbackend.repositories;

import com.princesan.advertisementmanagerbackend.model.entities.LogRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRecordRepository extends JpaRepository<LogRecord, Long> {
}