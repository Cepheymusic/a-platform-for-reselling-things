package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @NotNull
    private Instant createdAtInst;


    @Size(min = 8, max = 64)
    @Column(name = "comment_text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private AdEntity ad;
}

