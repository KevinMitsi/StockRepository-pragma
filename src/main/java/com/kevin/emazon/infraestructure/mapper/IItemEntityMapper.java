package com.kevin.emazon.infraestructure.mapper;

import com.kevin.emazon.domain.model.Item;
import com.kevin.emazon.infraestructure.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {IBrandEntityMapper.class, ICategoryEntityMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IItemEntityMapper {
    @Mapping(target = "name", source = "name")
    ItemEntity toItemEntity(Item item);

    Item toItem(ItemEntity itemEntity);
}
