package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CommentsList;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.impl.CommentService;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}/comments")
    public CommentsList getComments(@PathVariable int adId) {
        return commentService.getComments(adId);
    }

    @PostMapping("/{id}/comments")
    public Comment createComment(@PathVariable("adId") int adId, CreateOrUpdateComment createOrUpdateComment,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        return commentService.createComment(adId, createOrUpdateComment, userDetails);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("adId") int adId, @PathVariable("commentId") int commentId,
                                                @AuthenticationPrincipal UserDetails userDetails) {
        commentService.deleteComment(adId, commentId, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public Comment updateComment(@PathVariable("adId") int adId, @PathVariable("commentId") int commentId, @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        return commentService.updateComment(adId, commentId, createOrUpdateComment, userDetails);
    }
}
