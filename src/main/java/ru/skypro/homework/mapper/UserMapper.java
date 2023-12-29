package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Named("isIdToUrl")
    static String isIdToUrl(Integer id) {
        return "/image/" + id;
    }
    @Mapping(source = "imageEntity.id", target = "image", qualifiedByName = "isIdToUrl")
    User userToUserDTO(UserEntity userEntity);
}

