package com.princesan.advertisementmanagerbackend.model.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "banner")
@Getter
@Setter
public class Banner {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(message = "Banner's name can't be shorter than 2 or longer than 30 characters.",
            min = 2, max = 30)
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Length(message = "Banner's text can't be longer than 255 characters.", max = 255)
    @Column(nullable = false)
    private String textField;

    @NotNull
    @Digits(message = "Incorrect price format.", integer = 10, fraction = 2)
    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Size(message = "Banner should represent at least one category.", min = 1)
    @ManyToMany
    @JoinTable(name = "banner_category",
            joinColumns = @JoinColumn(name = "banner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories = new java.util.ArrayList<>();

    @NotNull
    @Column(nullable = false)
    private Boolean deleted = false;
}
