package com.kevin.emazon.application.dto;

import com.kevin.emazon.application.util.ConstantUtilClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @NotNull(message = ConstantUtilClass.CategoryErrorMsg.NN_NAME)
    @NotBlank(message = ConstantUtilClass.CategoryErrorMsg.NB_NAME)
    @Size(message = ConstantUtilClass.CategoryErrorMsg.S_NAME, min = 5, max = 50)
    private String name;


    @NotNull(message = ConstantUtilClass.CategoryErrorMsg.NN_DESCRIPTION)
    @NotBlank(message = ConstantUtilClass.CategoryErrorMsg.NB_DESCRIPTION)
    @Size(message = ConstantUtilClass.CategoryErrorMsg.S_DESCRIPTION, min = 5, max = 90)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDto that)) return false;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
