package ru.skypro.homework.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int authorId;
    private String authorImage;
    private String authorFirstName;
    private Long createdAt;
    private int pkIdComment;
    private String text;
}
