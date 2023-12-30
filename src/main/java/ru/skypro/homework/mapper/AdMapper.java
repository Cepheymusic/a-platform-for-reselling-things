package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

@Mapper
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "imageEntity.id", target = "image", qualifiedByName = "isIdToUrl")
    Ad adToAdDTO(AdEntity adEntity);

    AdEntity createOrUpdateAdDTOToAd(CreateOrUpdateAd createOrUpdateAd);

    @Named("isIdToUrl")
    static String isIdToUrl(int id) {
        return "/image/" + id;
    }
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "image", source = "imageEntity.id", qualifiedByName = "isIdToUrl")
    @Mapping(target = "phone", source = "author.phone")
    ExtendedAd adEntityToExtendedAdDTO(AdEntity adEntity);
}
