package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CommentsList;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import java.time.Instant;
import java.util.List;


@RestController
@RequestMapping("/ads/{adId}/comments")
public class CommentController {
    @GetMapping
    public CommentsList getComments() {
        return new CommentsList(1, List.of(new Comment(1, "",
                "Serzh", Instant.now().toEpochMilli(), 1, "text")));
    }

    @PostMapping
    public Comment createComment(@PathVariable("adId") int adId, CreateOrUpdateComment createOrUpdateComment) {
        return new Comment(1, "",
                "Serzh", Instant.now().toEpochMilli(), 1, "text");
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("adId") int adId, @PathVariable("commentId") int commentId) {
        return ResponseEntity.ok("OK");
    }

    @PatchMapping("{commentId}")
    public Comment updateComment(@PathVariable("adId") int adId, @PathVariable("commentId") int commentId) {
        return new Comment(1, "",
                "Serzh", Instant.now().toEpochMilli(), 1, "text");
    }
}
