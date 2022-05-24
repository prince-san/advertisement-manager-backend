package com.example.advertisementmanagerbackend.model.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public record BannerDto(
        @NotNull
        @Length(message = "Banner's name can't be shorter than 2 or longer than 30 characters.", min = 2, max = 30)
        String name,
        @NotNull
        @Length(message = "Banner's text can't be longer than 255 characters.", max = 255)
        String textField,
        @NotNull
        @Digits(message = "Incorrect price format.", integer = 10, fraction = 2)
        @PositiveOrZero
        BigDecimal price,
        @NotNull
        @Size(message = "Banner should represent at least one category.", min = 1)
        List<CategoryDto> categories
) implements Serializable {
}
