package com.kevin.emazon.application.mapper.response;

import com.kevin.emazon.application.dto.response.ItemResponseDto;
import com.kevin.emazon.application.mapper.IBrandDtoMapper;
import com.kevin.emazon.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {IBrandDtoMapper.class, ICategoryResponseDtoMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IItemResponseDtoMapper {
    @Mapping(target = "name", source = "name")
    ItemResponseDto itemToItemResponseDto(Item item);

}
