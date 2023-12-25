package ru.skypro.homework.service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CommentsList;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import java.time.Instant;


@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private AdRepository adRepository;

    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository,
                          AdRepository adRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.adRepository = adRepository;
    }

    public CommentsList getComments(int adId) {
        return (CommentsList) commentRepository.findByAdId(adId).stream().map(CommentMapper.INSTANCE::CommentToCommentDTO);
    }

    public Comment createComment(int adId, CreateOrUpdateComment createOrUpdateComment, UserDetails userDetails) {
        AdEntity adEntity = adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad is not found"));
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new RuntimeException());
        CommentEntity commentEntity = new CommentEntity(0, userEntity, "", Instant.now(), createOrUpdateComment.getText(), adEntity);
        commentRepository.save(commentEntity);
        return CommentMapper.INSTANCE.CommentToCommentDTO(commentEntity);
    }

    public void deleteComment(int adId, int commentId, UserDetails userDetails) {
        CommentEntity commentEntity = commentRepository.findById(adId).orElseThrow(() -> new RuntimeException());
        accessVerification(userDetails, commentEntity);
        //возможно ли удаление по id коммента????
        commentRepository.deleteById(commentId);
    }

    public Comment updateComment(int adId, int commentId, CreateOrUpdateComment createOrUpdateComment, UserDetails userDetails) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException());
        accessVerification(userDetails, commentEntity);
        commentEntity.setText(createOrUpdateComment.getText());
        commentRepository.save(commentEntity);
        return CommentMapper.INSTANCE.CommentToCommentDTO(commentEntity);
    }
    public void accessVerification(UserDetails userDetails, CommentEntity commentEntity) {
        if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                && !userDetails.getUsername().equals(commentEntity.getAuthor().getEmail())) {
            throw new RuntimeException();
        }
    }
}
