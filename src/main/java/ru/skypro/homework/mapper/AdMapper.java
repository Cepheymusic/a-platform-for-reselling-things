package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.entity.AdEntity;

@Mapper
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "id", target = "pkAdId")
    Ad adToAdDTO(AdEntity adEntity);

    @Mapping(target = "id", source = "pkAdId")
    @Mapping(source = "authorId", target = "author.id")
    @Mapping(target = "description", ignore = true)

    @Mapping(target = "comments", ignore = true)
    AdEntity adDTOToAd(Ad adDTO);

//    AdsList
//    CreateOrUpdateAd
//    ExtendedAd
}
