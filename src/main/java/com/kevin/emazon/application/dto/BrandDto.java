package com.kevin.emazon.application.dto;

import com.kevin.emazon.application.util.ConstantUtilClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
    @NotNull(message = ConstantUtilClass.BrandErrorMsg.NN_NAME)
    @NotBlank(message = ConstantUtilClass.BrandErrorMsg.NB_NAME)
    @Size(message = ConstantUtilClass.BrandErrorMsg.S_NAME, min = 3, max = 50)
    private String name;

    @NotNull(message = ConstantUtilClass.BrandErrorMsg.NN_DESCRIPTION)
    @NotBlank(message = ConstantUtilClass.BrandErrorMsg.NB_DESCRIPTION)
    @Size(message = ConstantUtilClass.BrandErrorMsg.S_DESCRIPTION, min = 5, max = 90)
    private String description;
}
