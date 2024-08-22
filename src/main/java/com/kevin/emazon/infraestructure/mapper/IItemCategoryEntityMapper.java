package com.kevin.emazon.infraestructure.mapper;

import com.kevin.emazon.domain.model.ItemCategory;
import com.kevin.emazon.infraestructure.entity.ItemCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {IItemEntityMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IItemCategoryEntityMapper {
    ItemCategoryEntity itemCategoryToItemCategoryEntity(ItemCategory itemCategory);
    ItemCategory itemCategoryEntityToItemCategory(ItemCategoryEntity itemCategoryEntity);
}
