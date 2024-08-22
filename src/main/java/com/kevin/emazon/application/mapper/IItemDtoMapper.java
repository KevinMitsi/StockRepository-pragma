package com.kevin.emazon.application.mapper;

import com.kevin.emazon.application.dto.ItemDto;
import com.kevin.emazon.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {IBrandDtoMapper.class, ICategoryDtoMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IItemDtoMapper {
    @Mapping(target = "name", source = "name")
    Item toItem(ItemDto itemDto);
}
