package com.princesan.advertisementmanagerbackend.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class LogRecord {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String ipAddress;

    @NotNull
    @Column(nullable = false)
    private String userAgent;

    @NotNull
    @Column(nullable = false)
    private Timestamp time;

    @ManyToOne
    @JoinColumn(name = "banner_id")
    private Banner banner;

    @ManyToMany
    @JoinTable(name = "log_record_category",
            joinColumns = @JoinColumn(name = "log_record_id", referencedColumnName = "id"))
    private List<Category> categories = new java.util.ArrayList<>();

    @Digits(message = "Incorrect price format.", integer = 10, fraction = 2)
    @PositiveOrZero
    private BigDecimal price;

    private String noContentReason;
}
