package com.princesan.advertisementmanagerbackend.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CategoryDto implements Serializable {
        private Long id;
        @NotNull
        @Length(message = "Category's name can't be shorter than 2 or longer than 30 characters.",
                min = 2, max = 30)
        @NotBlank(message = "Category's name can't be blank")
        private String name;
        @Pattern(message = "Category's request ID can only contain lowercase latin letters," +
                " numbers and \"_\" characters.",
                regexp = "[a-z0-9_]+")
        @Length(message = "Category's request ID can't be shorter than 2 or longer than 30 characters.",
                max = 30)
        private String requestId;
}
