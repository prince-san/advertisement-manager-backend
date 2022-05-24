package com.example.advertisementmanagerbackend.repositories;

import com.example.advertisementmanagerbackend.model.entities.LogRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRecordRepository extends JpaRepository<LogRecord, Long> {
}