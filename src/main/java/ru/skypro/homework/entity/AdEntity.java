package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @Column(nullable = false)
    private Integer price;

    @NotBlank
    @Size(min = 4, max = 32)
    @Column(nullable = false)
    private String title;

    @Size(min = 8, max = 64)
    @Column(length = 64)
    private String description;

    @OneToMany(mappedBy = "ad")
    private List<CommentEntity> comments;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_entity_id", nullable = false)
    private ImageEntity imageEntity;
}
