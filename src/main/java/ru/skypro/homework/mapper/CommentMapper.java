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

    @Mapping(target = "pkIdComment", source = "id")
    @Mapping(target = "authorImage", source = "author.image")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "createdAtInst", target = "createdAt", qualifiedByName = "instantToLong")
    Comment CommentToCommentDTO(CommentEntity commentEntity);

    @Mapping(target = "image", source = "authorImage")
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "id", source = "pkIdComment")
    @Mapping(target = "author.firstName", source = "authorFirstName")
    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "createdAt", target = "createdAtInst", qualifiedByName = "longToInstant")
    CommentEntity CommentDTOToComment(Comment commentDTO);

    @Named("longToInstant")
    public static Instant longToInstant(long createdAt) {
        return Instant.ofEpochMilli(createdAt);
    }

    @Named("instantToLong")
    public static Long instantToLong(Instant createdAtInst) {
        return createdAtInst.toEpochMilli();
    }

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAtInst", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "text", source = "text")
    CommentEntity commentEntityFromCreateOrUpdateComment(CreateOrUpdateComment createOrUpdateComment);

    @Mapping(target = "text", source = "text")
    CreateOrUpdateComment createOrUpdateCommentFromCommentEntity(CommentEntity commentEntity);

//    CommentsList
//            CreateOrUpdateComment
}
