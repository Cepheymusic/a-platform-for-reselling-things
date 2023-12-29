package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CommentsList;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.CommentEntity;

import java.time.Instant;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "authorImage", source = "author.imageEntity.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "createdAtInst", target = "createdAt", qualifiedByName = "instantToLong")
    Comment CommentToCommentDTO(CommentEntity commentEntity);

    @Named("instantToLong")
    static Long instantToLong(Instant createdAtInst) {
        return createdAtInst.toEpochMilli();
    }

}
