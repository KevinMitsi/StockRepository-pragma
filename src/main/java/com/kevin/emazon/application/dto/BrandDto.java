package com.kevin.emazon.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import static com.kevin.emazon.application.util.ConstantUtilClass.BrandErrorMsg.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
    @NotNull(message = NN_NAME)
    @NotBlank(message = NB_NAME)
    @Size(message = S_NAME, min = 3, max = 50)
    private String name;

    @NotNull(message = NN_DESCRIPTION)
    @NotBlank(message = NB_DESCRIPTION)
    @Size(message = S_DESCRIPTION, min = 5, max = 90)
    private String description;
}
