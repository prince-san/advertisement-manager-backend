package com.princesan.advertisementmanagerbackend.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(message = "Category's name can't be shorter than 2 or longer than 30 characters.",
            min = 2, max = 30)
    @NotBlank(message = "Category's name can't be blank")
    @Column(nullable = false, unique = true)
    private String name;

    @Pattern(message = "Category's request ID can only contain lowercase latin letters, numbers and \"_\" character.",
            regexp = "[a-z0-9_]+")
    @Length(message = "Category's request ID can't be shorter than 2 or longer than 30 characters.",
            min = 2, max = 30)
    @Column(nullable = false, unique = true, length = 30)
    private String requestId;

    @NotNull
    @Column(nullable = false)
    private Boolean deleted = false;
}
