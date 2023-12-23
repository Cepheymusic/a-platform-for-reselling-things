package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;

import ru.skypro.homework.dto.CommentsList;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.UserData;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.Instant;
import java.util.List;


@Service
public class CommentService {
    private CommentMapper commentMapper;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private AdRepository adRepository;
    private UserService userService;

    public CommentService(CommentMapper commentMapper,
                          CommentRepository commentRepository,
                          UserRepository userRepository,
                          AdRepository adRepository,
                          UserService userService) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.adRepository = adRepository;
        this.userService = userService;
    }

    public CommentsList getComments() {
        List<CommentEntity> list = commentRepository.findAll();
//        CommentsList commentsList = new CommentsList(1, list);//просит Comment вместо CommentEntity
//        return commentsList;
        return null;
    }

    public Comment createComment(int adId, CreateOrUpdateComment createOrUpdateComment, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad is not found"));
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException());
        CommentEntity commentEntity = new CommentEntity(0, userEntity, "", Instant.now(), createOrUpdateComment.getText(), adEntity);
        commentRepository.save(commentEntity);
        return commentMapper.CommentToCommentDTO(commentEntity);
    }

    public void deleteComment(int adId, int commentId, UserDetails userDetails) {
        CommentEntity commentEntity = commentRepository.findById(adId).orElseThrow(() -> new RuntimeException());
        userService.accessVerification(userDetails, commentEntity);
        //возможно ли удаление по id коммента????
        commentRepository.deleteById(commentId);
    }

    public Comment updateComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment, UserDetails userDetails) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException());
        userService.accessVerification(userDetails, commentEntity);
        commentEntity.setText(createOrUpdateComment.getText());
        commentRepository.save(commentEntity);
        return commentMapper.CommentToCommentDTO(commentEntity);
    }
}
