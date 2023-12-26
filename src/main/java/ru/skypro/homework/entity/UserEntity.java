package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2,max = 16)
    private String firstName;

    @NotBlank
    @Size(min = 2,max = 16)
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

    private String image;

    @Column(name = "user_role")
    private Role role;

    private String password;

    @OneToMany(mappedBy = "author")
    private List<AdEntity> ads;

    @OneToMany(mappedBy = "author")
    private List<CommentEntity> comments;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_entity_id")
    private ImageEntity imageEntity;
}

