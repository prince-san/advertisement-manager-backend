package com.princesan.advertisementmanagerbackend.repositories;

import com.princesan.advertisementmanagerbackend.model.entities.Category;
import com.princesan.advertisementmanagerbackend.model.entities.LogRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface LogRecordRepository extends JpaRepository<LogRecord, Long> {

    List<LogRecord> findAllByUserAgentAndIpAddress(@NotNull String userAgent, @NotNull String ipAddress);
}